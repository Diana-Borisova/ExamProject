package com.example.foodplanner.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.Payload;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ReservationDatesValidator.class)
public @interface ReservationDates {

    String message() default "Arrive date must be earlier that leave date";

    String first();

    String second();


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
