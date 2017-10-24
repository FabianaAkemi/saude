package com.tcc.saude.repository.helper.colaborador;

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

import com.tcc.saude.model.Colaborador;
import com.tcc.saude.repository.filter.ColaboradorFilter;
import com.tcc.saude.repository.paginacao.PaginacaoUtil;

public class ColaboradoresImpl implements ColaboradoresQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)	
	public Page<Colaborador> filtrar(ColaboradorFilter filtro, Pageable pageable) {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Colaborador.class);		
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));		

	}
	
	private Long total(ColaboradorFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Colaborador.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(ColaboradorFilter filtro, Criteria criteria) {
		
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (!StringUtils.isEmpty(filtro.getRg())){
				criteria.add(Restrictions.eq("rg", filtro.getRg()));
				
			}
			
			if (filtro.getAtividadeExercida() != null){
				criteria.add(Restrictions.eq("atividadeExercida", filtro.getAtividadeExercida()));
			}
		}
	}

	

}
