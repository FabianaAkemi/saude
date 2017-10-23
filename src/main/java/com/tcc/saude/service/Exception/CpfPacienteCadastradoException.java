package com.tcc.saude.service.Exception;

public class CpfPacienteCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CpfPacienteCadastradoException(String message){
		super(message);
	}
}
