package com.tcc.saude.repository.helper.consulta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tcc.saude.model.Consulta;
import com.tcc.saude.repository.filter.ConsultaFilter;

public interface ConsultasQueries {

	public Page<Consulta>  filtrar(ConsultaFilter consultaFilter, Pageable pageable);

}
