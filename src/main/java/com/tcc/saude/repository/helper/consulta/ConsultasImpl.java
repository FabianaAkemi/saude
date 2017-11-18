package com.tcc.saude.repository.helper.consulta;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.tcc.saude.model.Consulta;
import com.tcc.saude.repository.filter.ConsultaFilter;
import com.tcc.saude.repository.paginacao.PaginacaoUtil;

public class ConsultasImpl  implements ConsultasQueries{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)	
	public Page<Consulta> filtrar(ConsultaFilter filtro, Pageable pageable) {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Consulta.class);		
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));		

	}
	
	private Long total(ConsultaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Consulta.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(ConsultaFilter filtro, Criteria criteria) {
		criteria.createAlias("medico", "medico");
		criteria.createAlias("paciente", "paciente");
		if (filtro != null) {
			
			if (filtro.getDesde() != null) {
				LocalDateTime desde = LocalDateTime.of(filtro.getDesde(), LocalTime.of(0, 0));
				criteria.add(Restrictions.ge("dataCriacao", desde));
			}
			
			if (filtro.getAte() != null) {
				LocalDateTime ate = LocalDateTime.of(filtro.getAte(), LocalTime.of(23, 59));
				criteria.add(Restrictions.le("dataCriacao", ate));
			}
			
			if (!StringUtils.isEmpty(filtro.getNameMedico())) {
				criteria.add(Restrictions.ilike("medico.nome", filtro.getNameMedico(), MatchMode.ANYWHERE));
			}	
	
						
			if (!StringUtils.isEmpty(filtro.getNamePaciente())) {
				criteria.add(Restrictions.ilike("paciente.nome", filtro.getNamePaciente(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filtro.getCpfPaciente())) {
				criteria.add(Restrictions.eq("paciente.cpf", filtro.getRemoveFormatacao()));
			}
		}
	}

}
