package com.tcc.saude.repository.helper.usuario;

import java.util.Optional;
import java.util.List;

import com.tcc.saude.model.Usuario;

public interface UsuariosQueries {

	public Optional<Usuario> findEmailAndAtivos(String email);
	
	public List<String> findPermissoes( Usuario usuario);
}
