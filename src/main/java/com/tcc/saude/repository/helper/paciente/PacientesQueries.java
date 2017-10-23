package com.tcc.saude.repository.helper.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tcc.saude.model.Paciente;
import com.tcc.saude.repository.filter.PacienteFilter;


public interface PacientesQueries {

	public Page<Paciente> filtrar(PacienteFilter filtro, Pageable pageable);
}
