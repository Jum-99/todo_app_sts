package com.example.todoApp;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<Datev, String> {

	@Override
	public void initialize(Datev datev) {
	}
	
	@Override
	public boolean isValid(String input, ConstraintValidatorContext cxt) {
		if (input == null) {
			return false;
		}
		return input.matches("^[0-9]{4}/[0-9]{2}/[0-9]{2}$");
	}
}
