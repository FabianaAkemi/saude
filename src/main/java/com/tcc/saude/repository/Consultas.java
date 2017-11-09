package com.tcc.saude.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.saude.model.Consulta;
import com.tcc.saude.repository.helper.consulta.ConsultasQueries;

@Repository
public interface Consultas extends JpaRepository<Consulta, Long>, ConsultasQueries {


}
