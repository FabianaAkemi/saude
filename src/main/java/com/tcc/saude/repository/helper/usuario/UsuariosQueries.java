package com.tcc.saude.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tcc.saude.model.Usuario;
import com.tcc.saude.repository.filter.UsuarioFilter;

public interface UsuariosQueries {

	public Optional<Usuario> findEmailAndAtivos(String email);
	
	public List<String> findPermissoes( Usuario usuario);
	
	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);

	public Usuario buscarComGrupos(Long id);
}
