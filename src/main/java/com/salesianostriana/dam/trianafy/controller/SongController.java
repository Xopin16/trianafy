package com.salesianostriana.dam.trianafy.controller;

import com.salesianostriana.dam.trianafy.DTO.CreateSongDto;
import com.salesianostriana.dam.trianafy.DTO.SongDto;
import com.salesianostriana.dam.trianafy.DTO.SongDtoConverter;
import com.salesianostriana.dam.trianafy.model.Artist;
import com.salesianostriana.dam.trianafy.model.Song;
import com.salesianostriana.dam.trianafy.repos.ArtistRepository;
import com.salesianostriana.dam.trianafy.repos.SongRepository;
import com.salesianostriana.dam.trianafy.service.ArtistService;
import com.salesianostriana.dam.trianafy.service.SongService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SongController {
    private SongService songService;
    private SongRepository songRepo;
    //    private SongDto convertDto;
    private SongDtoConverter convertDto;
    private ArtistService artistService;

    @GetMapping("/song/")
    public ResponseEntity<List<Song>> findAll() {
        if (songService.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(songService.findAll());
        }
    }

    @GetMapping("/song/{id}")
    public ResponseEntity<Song> findOne(@PathVariable Long id) {

        return ResponseEntity.of(songService.findById(id));

    }

    @PostMapping("/song/")
    public ResponseEntity<Song> newSong(@RequestBody CreateSongDto dto) {
        if (dto.getArtistId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Song song = convertDto.SongDtoToSong(dto);

        Artist artist = artistService.findById(dto.getArtistId()).orElse(null);

        song.setArtist(artist);

        song = songRepo.save(song);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(song);
    }

    @PutMapping("/song/{id}")
    public ResponseEntity<Song> editSong(@RequestBody Song song, @PathVariable Long id) {
//        Modificar el contenido de una canción (salvo el id, que no se puede modificar).
//        Si se realiza correctamente, debe devolver 200 Ok. Si no existe, devolverá 404 Not Found.
//        Si hay algún error en los datos que nos envían, debe devolver 400 Bad Request
//        Para la petición, necesitarás un DTO como el de creación.
//        El modelo de respuesta será:
//        { “id”: 1, “title”: “The song”, “artist”: “Artist name”, “album” : “The album”,
//          “year”: 2000}
        return ResponseEntity.of(songService.findById(id).map(p -> {
                    p.setTitle(song.getTitle());
                    p.setAlbum(song.getAlbum());
                    p.setYear(song.getYear());
                    songRepo.save(p);
                    return p;
                })
        );

    }

    @DeleteMapping("/artist/{id}")
    public ResponseEntity<Artist> deleteSong(@PathVariable Long id){
        if(songRepo.existsById(id)){
            songService.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }
}
