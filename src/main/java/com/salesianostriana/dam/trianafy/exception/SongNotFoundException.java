package com.salesianostriana.dam.trianafy.exception;

import javax.persistence.EntityNotFoundException;

public class SongNotFoundException extends EntityNotFoundException {

    public SongNotFoundException() {
        super("The song could not be found");
    }

    public SongNotFoundException(Long id) {
        super(String.format("The song with id %d could not be found", id));
    }

}
