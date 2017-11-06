package com.tcc.saude.repository.helper.triagem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tcc.saude.model.Triagem;
import com.tcc.saude.repository.filter.TriagemFilter;

public interface TriagensQueries {

	public Page<Triagem> filtrar(TriagemFilter filtro, Pageable pageable);

}
