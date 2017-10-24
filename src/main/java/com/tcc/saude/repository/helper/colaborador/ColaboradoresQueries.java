package com.tcc.saude.repository.helper.colaborador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tcc.saude.model.Colaborador;
import com.tcc.saude.repository.filter.ColaboradorFilter;

public interface ColaboradoresQueries {
	
	public Page<Colaborador> filtrar(ColaboradorFilter colaboradorFilter, Pageable pageable);
}
