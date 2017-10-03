package com.tcc.saude.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.saude.model.Medico;

@Repository
public interface Medicos extends JpaRepository<Medico, Long>{
	
	public Optional<Medico> findById(Long id);


}
