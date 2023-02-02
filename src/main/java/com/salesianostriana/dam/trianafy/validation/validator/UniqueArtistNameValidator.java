package com.salesianostriana.dam.trianafy.validation.validator;

import com.salesianostriana.dam.trianafy.repos.ArtistRepository;
import com.salesianostriana.dam.trianafy.validation.annotation.UniqueArtistName;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueArtistNameValidator implements ConstraintValidator<UniqueArtistName, Object> {

    @Autowired
    private ArtistRepository repository;

    private String name;

    @Override
    public void initialize(UniqueArtistName constraintAnnotation) {
        this.name = constraintAnnotation.name();

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        return repository.existsByName(name);
    }
}
