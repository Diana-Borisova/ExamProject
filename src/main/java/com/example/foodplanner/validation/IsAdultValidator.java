package com.example.foodplanner.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class IsAdultValidator implements ConstraintValidator<IsAdult, LocalDate> {
    @Override
    public void initialize(IsAdult constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null) {
            return false;
        }
        return Period.between(localDate, LocalDate.now()).getYears() >= 18;
    }
}
