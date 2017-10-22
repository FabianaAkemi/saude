package com.tcc.saude.service.Exception;

public class SenhaObrigatoriaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SenhaObrigatoriaException(String message){
		super(message);
	}
}