package com.salesianostriana.dam.trianafy.controller;

import com.salesianostriana.dam.trianafy.DTO.CreateSongDto;
import com.salesianostriana.dam.trianafy.model.Song;
import com.salesianostriana.dam.trianafy.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class SongValidationController {

    private final SongService songService;



    @PostMapping("/song/new")
    public ResponseEntity<Song> nuevoSong(@Valid @RequestBody CreateSongDto songDto) {
        Song created = songService.save(songDto);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();


        return ResponseEntity
                .created(createdURI)
                .body(created);
    }


}
