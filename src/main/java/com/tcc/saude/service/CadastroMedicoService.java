package com.tcc.saude.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.saude.model.Medico;
import com.tcc.saude.repository.Medicos;
import com.tcc.saude.service.Exception.CrmMedicoCadastradoException;

@Service
public class CadastroMedicoService {

	@Autowired
	private Medicos medicos;
	
	@Transactional
	public void salvar(Medico medico ){
		
		Optional<Medico> medicoExistente = medicos.findByCrm(medico.getCrm());
		
		if(medicoExistente.isPresent()){
			throw new CrmMedicoCadastradoException("Medico já cadastrado");			

		} 

		medicos.save(medico);		
	}
}
