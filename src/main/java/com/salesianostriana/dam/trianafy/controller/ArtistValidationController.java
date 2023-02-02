package com.salesianostriana.dam.trianafy.controller;

import com.salesianostriana.dam.trianafy.DTO.CreateArtistDto;
import com.salesianostriana.dam.trianafy.model.Artist;
import com.salesianostriana.dam.trianafy.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArtistValidationController {

    private final ArtistService artistService;

    @GetMapping("/artist/new")
    public List<Artist> obtenerTodos() {
        return artistService.findAllArtist();
    }

    @GetMapping("/artist/new/{id}")
    public Artist obtenerUno(@Valid @PathVariable Long id) {
        return artistService.findByIdArtist(id);
    }

    @PostMapping("/artist/new")
    public ResponseEntity<Artist> nuevoArtist(@Valid @RequestBody CreateArtistDto artistDto) {
        Artist created = artistService.save(artistDto);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();


        return ResponseEntity
                .created(createdURI)
                .body(created);
    }

    @PutMapping("/artist/new/{id}")
    public Artist editarArtist(@Valid @RequestBody CreateArtistDto artistDto, @PathVariable Long id) {
        return artistService.editArtist(id, artistDto);
    }

}
