package com.spring.apivendas.validation.contraintValidation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Service;

import com.spring.apivendas.validation.NotEmptyList;

import java.util.List;

public class NotEmptyListValidator
        implements ConstraintValidator<NotEmptyList, List>{

    @Override
    public boolean isValid(List list,
                           ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }

    @Override
    public void initialize( NotEmptyList constraintAnnotation ) {
    }
}
