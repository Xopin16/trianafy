package com.salesianostriana.dam.trianafy.controller;

import com.salesianostriana.dam.trianafy.DTO.CreatePlaylistDto;
import com.salesianostriana.dam.trianafy.DTO.PlaylistDto;
import com.salesianostriana.dam.trianafy.DTO.PlaylistDtoConverter;
import com.salesianostriana.dam.trianafy.model.Playlist;
import com.salesianostriana.dam.trianafy.model.Song;
import com.salesianostriana.dam.trianafy.repos.PlaylistRepository;
import com.salesianostriana.dam.trianafy.service.PlaylistService;
import com.salesianostriana.dam.trianafy.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistRepository playlistRepo;
    private final PlaylistService playlistService;

    private final PlaylistDtoConverter dtoConverter;

    private final SongService songService;

    @GetMapping("/list/")
    public ResponseEntity<List<PlaylistDto>> findAll() {
        List<Playlist> data = playlistService.findAll();

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<PlaylistDto> result =
                    data.stream()
                            .map(PlaylistDto::of)
                            .collect(Collectors.toList());
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Playlist> findOne(@PathVariable Long id) {

        return ResponseEntity.of(playlistService.findById(id));

    }

    @PostMapping("/list/{id}")
    public ResponseEntity<CreatePlaylistDto> newPlaylist(@RequestBody CreatePlaylistDto dto){
        if (dto.getSongId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Playlist nueva = dtoConverter.CreatePlaylistDtoToPlaylist(dto);

        Song song = songService.findById(dto.getSongId()).orElse(null);

        nueva = playlistRepo.save(nueva);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dtoConverter.playlistToPlaylistDto(nueva));

    }


    @PutMapping("/list/{id}")
    public ResponseEntity<PlaylistDto> editPlaylist(@RequestBody PlaylistDto dto, @PathVariable Long id) {

        return ResponseEntity.of(playlistService.findById(id).map(p -> {
                    p.setName(dto.getName());
                    return PlaylistDto.of(p);
                })
        );
    }

    @DeleteMapping("/list/{id}")
    public ResponseEntity<Playlist> deletePlaylist(@PathVariable Long id){
        if(playlistRepo.existsById(id)) {
            playlistService.deleteById(id);
        }

        return ResponseEntity.noContent().build();
    }

    



}
