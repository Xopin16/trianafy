package com.salesianostriana.dam.trianafy.validation.annotation;

import com.salesianostriana.dam.trianafy.validation.validator.UniqueArtistNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueArtistNameValidator.class)
@Documented
public @interface UniqueArtistName {

    String message() default "No puedes agregar el mismo artista dos veces.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String name();
}
