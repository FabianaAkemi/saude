package com.tcc.saude.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.saude.model.Colaborador;
import com.tcc.saude.repository.helper.colaborador.ColaboradoresQueries;

@Repository
public interface Colaboradores extends JpaRepository<Colaborador, Long>, ColaboradoresQueries{

	Optional<Colaborador> findByRg(String rg);	

}
