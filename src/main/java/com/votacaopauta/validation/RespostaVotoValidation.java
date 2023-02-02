package com.votacaopauta.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RespostaVotoValidation implements ConstraintValidator<RespostaVoto, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String respostaVoto = value;
		return respostaVoto.equals("Sim") || respostaVoto.equals("Não") ;
	}
}
