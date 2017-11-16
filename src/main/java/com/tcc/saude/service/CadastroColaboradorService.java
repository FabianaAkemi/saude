package com.tcc.saude.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.saude.model.Colaborador;
import com.tcc.saude.repository.Colaboradores;
import com.tcc.saude.service.Exception.ImpossivelExcluirEntidadeException;
import com.tcc.saude.service.Exception.RgColaboradorCadastradoException;

@Service
public class CadastroColaboradorService {
	
	@Autowired
	private Colaboradores colaboradores;

	@Transactional
	public void salvar(Colaborador colaborador) {

		Optional<Colaborador> colaboradorExistente = colaboradores.findByRg(colaborador.getRg());
		
		if(colaboradorExistente.isPresent() && colaborador.isNovo()){
			throw new RgColaboradorCadastradoException("Colaborador já cadastrado!");		
		} 

		colaboradores.save(colaborador);
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			colaboradores.delete(id);
			colaboradores.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar o Colaborador. Já foi usada em alguma atividade.");
		}		
	}

}
