package com.dev.cinema.validator;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, Object> {
    private String password;
    private String repeatPassword;

    public PasswordValidator(ValidPassword validPassword) {
        this.password = validPassword.password();
        this.repeatPassword = validPassword.repeatPassword();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        Object dtoPassword = new BeanWrapperImpl(object)
                .getPropertyValue(password);
        Object dtoRepeatPassword = new BeanWrapperImpl(object)
                .getPropertyValue(repeatPassword);
        return dtoPassword != null && dtoPassword.equals(dtoRepeatPassword);
    }
}
