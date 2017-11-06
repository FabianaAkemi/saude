package com.tcc.saude.service.Exception;

public class PacienteNuloException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PacienteNuloException(String message){
		super(message);
	}
}