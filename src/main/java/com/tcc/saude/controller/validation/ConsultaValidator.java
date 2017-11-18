package com.tcc.saude.controller.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tcc.saude.model.Consulta;

@Component
public class ConsultaValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Consulta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "paciente.id", "","Selecione um Paciente na pesquisa rápida");
		ValidationUtils.rejectIfEmpty(errors, "medico.id", "","Selecione um Médico na pesquisa rápida");
	
		
		Consulta consulta = (Consulta) target;
		
		if(consulta.getDescricao().isEmpty()){
			errors.reject("", "Adicione uma descrição");
		}
	}

}
