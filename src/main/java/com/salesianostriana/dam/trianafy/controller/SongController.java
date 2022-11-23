package com.salesianostriana.dam.trianafy.controller;

import com.salesianostriana.dam.trianafy.DTO.SongDto;
import com.salesianostriana.dam.trianafy.model.Artist;
import com.salesianostriana.dam.trianafy.model.Song;
import com.salesianostriana.dam.trianafy.repos.ArtistRepository;
import com.salesianostriana.dam.trianafy.service.ArtistService;
import com.salesianostriana.dam.trianafy.service.SongService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SongController {
    private SongService songService;
    private ArtistRepository songRepo;
    private SongDto convertDto;

    @GetMapping("/song/")
    public ResponseEntity<List<Song>> obtenerTodos() {
        if (songService.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(songService.findAll());
        }
    }

    @GetMapping("/song/{id}")
    public ResponseEntity<Song> obtenerUno(@PathVariable Long id) {

        return ResponseEntity.of(songService.findById(id));

    }

//    @PostMapping("/song/")
//    public ResponseEntity<SongDto> nuevoArtist(@RequestBody SongDto dto) {
//        if(dto.getArtistId() == null){
//            return ResponseEntity.badRequest().build();
//        }
//
//        Song song = of()
//    }

//    @PutMapping("/song/{id}")
//    public ResponseEntity<Artist> editarArtist(@RequestBody Song song, @PathVariable Long id) {
//        return ResponseEntity.of(songService.findById(id).map(p -> {
//            p.setName(artist.getName());
//            songRepo.save(p);
//            return p;
//        }));
//    }
//
//    @DeleteMapping("/artist/{id}")
//    public ResponseEntity<Artist> borrarArtist(@PathVariable Long id){
//        if(artistRepo.existsById(id)){
//            artistService.deleteById(id);
//        }
//        return ResponseEntity.status()
//    }
}
