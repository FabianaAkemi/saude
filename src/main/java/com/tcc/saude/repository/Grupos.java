package com.tcc.saude.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.saude.model.Grupo;

@Repository
public interface Grupos extends JpaRepository<Grupo, Long>{

}
