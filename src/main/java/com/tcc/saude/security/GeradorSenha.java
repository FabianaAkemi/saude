package com.tcc.saude.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {

  public static void main(String[] args) {
	BCryptPasswordEncoder gerador = new  BCryptPasswordEncoder();

	System.out.println(gerador.encode("vader"));			
			
}
}
