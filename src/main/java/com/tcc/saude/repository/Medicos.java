package com.tcc.saude.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.saude.model.Medico;
import com.tcc.saude.repository.helper.medico.MedicosQueries;


@Repository
public interface Medicos extends JpaRepository<Medico, Long>, MedicosQueries {

	Optional<Medico> findByCrm(String crm);
	



}
