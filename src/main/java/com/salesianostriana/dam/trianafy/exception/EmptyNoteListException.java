package com.salesianostriana.dam.trianafy.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyNoteListException extends EntityNotFoundException {

    public EmptyNoteListException() {
        super("No notes were found with the search criteria");
    }


}
