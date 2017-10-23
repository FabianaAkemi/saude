package com.tcc.saude.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.tcc.saude.model.Usuario;
import com.tcc.saude.repository.Usuarios;
import com.tcc.saude.service.Exception.EmailUsuarioCadastradoException;
import com.tcc.saude.service.Exception.SenhaObrigatoriaException;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void salvar(Usuario usuario) {
		
		Optional<Usuario> usuarioExistente = usuarios.findByEmail(usuario.getEmail());
		
		if(usuarioExistente.isPresent()){
			throw new EmailUsuarioCadastradoException("Usuário já cadastrado");			

		}
		
		if(usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha()) ){
			throw new SenhaObrigatoriaException("Senha de novo usuário é obrigatoria");			

		}
		
		if(usuario.isNovo()){
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmacaoSenha(usuario.getSenha());
		}
		
		usuarios.save(usuario);
		
	}
	
	

}
