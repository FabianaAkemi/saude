package com.tcc.saude.repository.helper.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tcc.saude.model.Medico;
import com.tcc.saude.repository.filter.MedicoFilter;

public interface MedicosQueries {
	
	public Page<Medico> filtrar(MedicoFilter filtro, Pageable pageable);


}
