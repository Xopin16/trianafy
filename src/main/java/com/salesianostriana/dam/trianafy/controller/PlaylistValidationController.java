package com.salesianostriana.dam.trianafy.controller;

import com.salesianostriana.dam.trianafy.DTO.CreatePlaylistDto;
import com.salesianostriana.dam.trianafy.DTO.PlaylistDto;
import com.salesianostriana.dam.trianafy.model.Playlist;
import com.salesianostriana.dam.trianafy.service.PlaylistService;
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
public class PlaylistValidationController {

    private final PlaylistService playlistService;

    @GetMapping("/list/new")
    public List<Playlist> findAll() {
        return playlistService.findAllPlaylist();
    }

    @GetMapping("/list/new/{id}")
    public Playlist findOne(@PathVariable Long id) {
        return playlistService.findByIdPlaylist(id);
    }

    @PutMapping("/list/new/{id}")
    public Playlist editPlaylist(@RequestBody CreatePlaylistDto dto, @PathVariable Long id) {
        return playlistService.editPlaylist(id, dto);
    }

    @PostMapping("/list/new")
    public ResponseEntity<Playlist> nuevoPlaylist(@Valid @RequestBody CreatePlaylistDto playlistDto) {
        Playlist created = playlistService.save(playlistDto);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();


        return ResponseEntity
                .created(createdURI)
                .body(created);
    }
}
