package com.tcc.saude.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.saude.model.Usuario;
import com.tcc.saude.repository.helper.usuario.UsuariosQueries;



public interface Usuarios  extends JpaRepository<Usuario, Long>, UsuariosQueries{

	Optional<Usuario> findByEmail(String email);

}
