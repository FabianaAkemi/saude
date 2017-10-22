package com.tcc.saude.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.saude.model.Usuario;


@Repository
public interface Usuarios  extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);

}
