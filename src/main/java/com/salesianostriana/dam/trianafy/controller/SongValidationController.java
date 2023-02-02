package com.salesianostriana.dam.trianafy.controller;

import com.salesianostriana.dam.trianafy.DTO.CreateSongDto;
import com.salesianostriana.dam.trianafy.DTO.SongArtistDto;
import com.salesianostriana.dam.trianafy.DTO.SongDto;
import com.salesianostriana.dam.trianafy.model.Song;
import com.salesianostriana.dam.trianafy.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SongValidationController {

    private final SongService songService;


    @GetMapping("/song/new")
    public List<Song> findAll() {
        return songService.findAllSongs();
    }

    @GetMapping("/song/new/{id}")
    public Song findOne(@PathVariable Long id) {

        return songService.findByIdSong(id);

    }

    @PutMapping("/song/new/{id}")
    public Song editSong(@RequestBody CreateSongDto dto, @PathVariable Long id) {
        return songService.editSong(id, dto);
    }

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
