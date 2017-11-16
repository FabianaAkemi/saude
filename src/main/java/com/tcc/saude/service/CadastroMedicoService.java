package com.tcc.saude.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.saude.model.Medico;
import com.tcc.saude.repository.Medicos;
import com.tcc.saude.service.Exception.CrmMedicoCadastradoException;
import com.tcc.saude.service.Exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroMedicoService {

	@Autowired
	private Medicos medicos;
	
	@Transactional
	public void salvar(Medico medico ){
		
		Optional<Medico> medicoExistente = medicos.findByCrm(medico.getCrm());
		
		if(medicoExistente.isPresent() && medico.isNovo()){
			throw new CrmMedicoCadastradoException("Medico já cadastrado");			

		} 

		medicos.save(medico);		
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			medicos.delete(id);
			medicos.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar o Médico. Já foi usada em uma consulta.");
		}
	}
}
