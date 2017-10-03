package com.tcc.saude.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.saude.model.Paciente;

@Repository
public interface Pacientes extends JpaRepository<Paciente, Long> {

	public Optional<Paciente> findById(Long id);

}
