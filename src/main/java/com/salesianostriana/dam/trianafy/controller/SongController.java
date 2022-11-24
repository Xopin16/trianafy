package com.salesianostriana.dam.trianafy.controller;

import com.salesianostriana.dam.trianafy.DTO.CreateSongDto;
import com.salesianostriana.dam.trianafy.DTO.SongDto;
import com.salesianostriana.dam.trianafy.DTO.SongDtoConverter;
import com.salesianostriana.dam.trianafy.model.Artist;
import com.salesianostriana.dam.trianafy.model.Song;
import com.salesianostriana.dam.trianafy.repos.SongRepository;
import com.salesianostriana.dam.trianafy.service.ArtistService;
import com.salesianostriana.dam.trianafy.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SongController {
    private SongService songService;
    private SongRepository songRepo;
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
    public ResponseEntity<SongDto> newSong(@RequestBody CreateSongDto dto) {
        if (dto.getArtistId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Song song = convertDto.CreateSongDtoToSong(dto);

        Artist artist = artistService.findById(dto.getArtistId()).orElse(null);

        song.setArtist(artist);

        song = songRepo.save(song);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(convertDto.songToSongDto(song));
    }

    @PutMapping("/song/{id}")
    public ResponseEntity<SongDto> editSong(@RequestBody SongDto dto, @PathVariable Long id) {

        return ResponseEntity.of(songService.findById(id).map(p -> {
                    p.setTitle(dto.getTitle());
                    p.getArtist().setName(dto.getArtist());
                    p.setAlbum(dto.getAlbum());
                    p.setYear(dto.getYear());
                    songRepo.save(p);
                    return convertDto.songToSongDto(p);
                })
        );

    }

    @DeleteMapping("/artist/{id}")
    public ResponseEntity<Artist> deleteSong(@PathVariable Long id) {
        if (songRepo.existsById(id)) {
            songService.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }
}
