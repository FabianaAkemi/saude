package com.tcc.saude.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.saude.model.Triagem;
import com.tcc.saude.repository.Triagens;

@Service
public class CadastroTriagemService {

	
	@Autowired
	private Triagens triagens;

	@Transactional
	public void salvar(Triagem triagem) {
		
		triagem.setDataCriacao(LocalDateTime.now());
		triagens.save(triagem);
		
	}
	

}
