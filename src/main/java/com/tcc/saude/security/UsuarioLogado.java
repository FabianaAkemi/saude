package com.tcc.saude.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.tcc.saude.model.Usuario;

public class UsuarioLogado extends User {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;

	public UsuarioLogado(Usuario usuario , Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail() ,usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	

}
