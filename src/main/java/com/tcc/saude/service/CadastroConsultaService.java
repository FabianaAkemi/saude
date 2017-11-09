package com.tcc.saude.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.saude.model.Consulta;
import com.tcc.saude.repository.Consultas;

@Service
public class CadastroConsultaService {

	@Autowired
	private Consultas consultas;

	@Transactional
	public void salvar(Consulta consulta) {
		
		consulta.setDataCriacao(LocalDateTime.now());
		consultas.save(consulta);
		
	}

}
