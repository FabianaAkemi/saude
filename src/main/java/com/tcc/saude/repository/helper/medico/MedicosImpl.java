package com.tcc.saude.repository.helper.medico;

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

import com.tcc.saude.model.Medico;
import com.tcc.saude.repository.filter.MedicoFilter;
import com.tcc.saude.repository.paginacao.PaginacaoUtil;

public class MedicosImpl implements MedicosQueries{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Medico> filtrar(MedicoFilter filtro, Pageable pageable) {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Medico.class);		
					
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
		

	}
	
	private Long total(MedicoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Medico.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(MedicoFilter filtro, Criteria criteria) {
		
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (!StringUtils.isEmpty(filtro.getCrm())){
				criteria.add(Restrictions.eq("crm", filtro.getCrm()));
				
			}
			
			if (filtro.getTipoEspecialista() != null){
				criteria.add(Restrictions.eq("tipoEspecialista", filtro.getTipoEspecialista()));
			}
		}
	}
	

}
