package com.dev.cinema.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator <ValidEmail, String> {
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email!=null && email.matches(EMAIL_REGEX);
    }
}
