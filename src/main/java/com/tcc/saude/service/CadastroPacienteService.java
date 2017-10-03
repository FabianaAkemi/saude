package com.tcc.saude.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.saude.model.Paciente;
import com.tcc.saude.repository.Pacientes;

@Service
public class CadastroPacienteService {

	@Autowired
	private Pacientes pacientes;
	
	@Transactional
	public void salvar(Paciente paciente){
		pacientes.save(paciente);		
	}
}
