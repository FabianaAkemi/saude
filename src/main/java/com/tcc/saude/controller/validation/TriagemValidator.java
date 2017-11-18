package com.tcc.saude.controller.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tcc.saude.model.Triagem;

@Component
public class TriagemValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Triagem.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "paciente.id", "","Selecione um Paciente na pesquisa rápida");
		
	}

}
