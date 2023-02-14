package com.ead.authuser.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameConstraintImpl implements ConstraintValidator<UsernameConstraint, String> {

	@Override
	public void initialize(UsernameConstraint ConstraintAnnotation) {
		ConstraintValidator.super.initialize(ConstraintAnnotation);
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
		if(username == null || username.trim().isEmpty() || username.contains(" ")) {
			return false;
		}
		return true;
	}

}
