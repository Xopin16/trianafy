package com.salesianostriana.dam.trianafy.validation.annotation;

import com.salesianostriana.dam.trianafy.validation.validator.FieldsValueMatchValidator;
import com.salesianostriana.dam.trianafy.validation.validator.UniqueArtistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueArtistValidator.class)
@Documented
public @interface UniqueArtist {

    String message() default "No puedes agregar el mismo artista dos veces.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    long id();
}
