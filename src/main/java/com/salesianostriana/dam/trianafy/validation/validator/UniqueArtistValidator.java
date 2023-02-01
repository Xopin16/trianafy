package com.salesianostriana.dam.trianafy.validation.validator;

import com.salesianostriana.dam.trianafy.repos.ArtistRepository;
import com.salesianostriana.dam.trianafy.validation.annotation.UniqueArtist;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueArtistValidator implements ConstraintValidator<UniqueArtist, Object> {

    @Autowired
    private ArtistRepository repository;

    private Long id;

    @Override
    public void initialize(UniqueArtist constraintAnnotation) {
        this.id = constraintAnnotation.id();

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        return repository.existsById(id);
    }
}
