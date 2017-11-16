package com.tcc.saude.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.saude.model.Paciente;
import com.tcc.saude.repository.Pacientes;
import com.tcc.saude.service.Exception.CpfPacienteCadastradoException;
import com.tcc.saude.service.Exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroPacienteService {

	@Autowired
	private Pacientes pacientes;
	
	@Transactional
	public void salvar(Paciente paciente){
		
		Optional<Paciente> pacienteExistente = pacientes.findByCpf(paciente.removeFormatacao());
		
		if(pacienteExistente.isPresent() && paciente.isNovo()){
			throw new CpfPacienteCadastradoException("Paciente já cadastrado");			
			
		}
		pacientes.save(paciente);	
	}

	@Transactional
	public void excluir(Long id) {
		try {
			pacientes.delete(id);
			pacientes.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar o Paciente. Já foi usada em uma consulta ou triagem.");
		}		
	}
	
	
}
