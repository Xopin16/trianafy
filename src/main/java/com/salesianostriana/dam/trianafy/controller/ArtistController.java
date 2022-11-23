package com.salesianostriana.dam.trianafy.controller;

import com.salesianostriana.dam.trianafy.model.Artist;
import com.salesianostriana.dam.trianafy.repos.ArtistRepository;
import com.salesianostriana.dam.trianafy.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ArtistController {

    private ArtistService artistService;
    private ArtistRepository artistRepo;

    @GetMapping("/artist/")
    public ResponseEntity<List<Artist>> obtenerTodos() {
        if (artistService.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(artistService.findAll());
        }
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<Artist> obtenerUno(@PathVariable Long id) {

        return ResponseEntity.of(artistService.findById(id));

    }

    @PostMapping("/artist/")
    public ResponseEntity<Artist> nuevoArtist(@RequestBody Artist artist) {
        if (artist.getName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else
            return ResponseEntity.status(HttpStatus.CREATED).body(artistRepo.save(artist));
    }

    @PutMapping("/artist/{id}")
    public ResponseEntity<Artist> editarArtist(@RequestBody Artist artist, @PathVariable Long id) {
        return ResponseEntity.of(artistService.findById(id).map(p -> {
            p.setName(artist.getName());
            artistRepo.save(p);
            return p;
        }));
    }

//    @DeleteMapping("/artist/{id}")
//    public ResponseEntity<Artist> borrarArtist(@PathVariable Long id){
//        if(artistRepo.existsById(id)){
//            artistService.deleteById(id);
//        }
//        return ResponseEntity.status()
//    }

}
