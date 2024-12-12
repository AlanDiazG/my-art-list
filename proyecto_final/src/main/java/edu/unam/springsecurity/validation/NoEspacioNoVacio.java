package edu.unam.springsecurity.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NoEspacioNoVacioValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface NoEspacioNoVacio {
    String message() default "No debe ser vacío";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
