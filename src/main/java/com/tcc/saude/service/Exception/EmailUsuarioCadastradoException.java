package com.tcc.saude.service.Exception;

public class EmailUsuarioCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailUsuarioCadastradoException(String message){
		super(message);
	}
}