package com.tcc.saude.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.saude.model.Triagem;
import com.tcc.saude.repository.helper.triagem.TriagensQueries;

@Repository
public interface Triagens extends JpaRepository<Triagem, Long>, TriagensQueries {

}
