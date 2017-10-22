package com.tcc.saude.validation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import com.tcc.saude.validation.validator.AtributoSenhaValidator;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { AtributoSenhaValidator.class })
public @interface AtributoSenha {
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "Atributos não conferem";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String atributo();
	
	String atributoConfirmacao();

}
