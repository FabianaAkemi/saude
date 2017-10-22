package com.tcc.saude.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.beanutils.BeanUtils;

import com.tcc.saude.validation.AtributoSenha;

public class AtributoSenhaValidator implements ConstraintValidator<AtributoSenha, Object>{

	private String atributo;	
	private String atributoConfirmacao;
	
	@Override
	public void initialize(AtributoSenha constraintAnnotation) {

		this.atributo = constraintAnnotation.atributo();
		this.atributoConfirmacao = constraintAnnotation.atributoConfirmacao();
		
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		
		boolean correspondente = false;
		
		try{
			Object valorAtributo = BeanUtils.getProperty(object, this.atributo);
			Object valorAtributoConfirmacao = BeanUtils.getProperty(object, this.atributoConfirmacao);
			
			correspondente = bothAreNull(valorAtributo, valorAtributoConfirmacao) ||
					bothAreEquals(valorAtributo, valorAtributoConfirmacao);
			
			
		}catch (Exception e) {
			throw new RuntimeException("Erro ao recuperar valores das senhas",e);
		}		
		
		if(!correspondente){
			
			context.disableDefaultConstraintViolation();
			String mensagem =  context.getDefaultConstraintMessageTemplate();
			ConstraintViolationBuilder violationBuilder = context.buildConstraintViolationWithTemplate(mensagem);
			violationBuilder.addPropertyNode(atributoConfirmacao).addConstraintViolation();
		}
		
		return correspondente;
	}


	private boolean bothAreNull(Object valorAtributo, Object valorAtributoConfirmacao) {

		return valorAtributo == null && valorAtributoConfirmacao == null;
	}
	
	private boolean bothAreEquals(Object valorAtributo, Object valorAtributoConfirmacao) {
		return valorAtributo !=null && valorAtributo.equals(valorAtributoConfirmacao);
	}

}
