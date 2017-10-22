package com.tcc.saude.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.saude.model.Paciente;
import com.tcc.saude.repository.helper.paciente.PacientesQueries;

@Repository
public interface Pacientes extends JpaRepository<Paciente, Long>, PacientesQueries  {

	public Optional<Paciente> findByCpf(String cpf);

}
