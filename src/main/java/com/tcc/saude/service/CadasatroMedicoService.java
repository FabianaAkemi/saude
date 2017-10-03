package com.tcc.saude.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.saude.model.Medico;
import com.tcc.saude.repository.Medicos;

@Service
public class CadasatroMedicoService {

	@Autowired
	private Medicos medicos;
	
	@Transactional
	public void salvar(Medico medico ){
		medicos.save(medico);		
	}
}
