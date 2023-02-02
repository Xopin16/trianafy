package com.salesianostriana.dam.trianafy.exception;

import com.salesianostriana.dam.trianafy.model.Playlist;

import javax.persistence.EntityNotFoundException;

public class PlaylistNotFoundException extends EntityNotFoundException {

    public PlaylistNotFoundException() {
        super("The playlist could not be found");
    }

    public PlaylistNotFoundException(Long id) {
        super(String.format("The playlist with id %d could not be found", id));
    }

}
