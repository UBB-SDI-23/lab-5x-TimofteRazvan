package com.example.FirstSpring.Validator;

import com.example.FirstSpring.Entity.Spouse;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.server.ServerWebInputException;

import java.util.stream.Collectors;

public class SpouseValidator implements Validator {
    public SpouseValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Spouse.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"name","name.required");
        Spouse spouse = (Spouse) target;
        if(spouse.getAge()<18)
            errors.rejectValue("age","age.underage");
        if(spouse.getPhone().length() < 9)
            errors.rejectValue("phone","phone.impossible");
        if(errors.hasErrors())
            throw new ServerWebInputException(errors.getAllErrors().stream().map(e->e.getCode()).collect(Collectors.toList()).toString());
    }
}
