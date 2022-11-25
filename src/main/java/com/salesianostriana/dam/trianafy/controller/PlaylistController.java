package com.salesianostriana.dam.trianafy.controller;

import com.salesianostriana.dam.trianafy.DTO.*;
import com.salesianostriana.dam.trianafy.model.Playlist;
import com.salesianostriana.dam.trianafy.model.Song;
import com.salesianostriana.dam.trianafy.repos.PlaylistRepository;
import com.salesianostriana.dam.trianafy.repos.SongRepository;
import com.salesianostriana.dam.trianafy.service.PlaylistService;
import com.salesianostriana.dam.trianafy.service.SongService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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

    private final SongDtoConverter songDtoConverter;

    private final SongRepository songRepo;

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
    public ResponseEntity<CreatePlaylistDto> newPlaylist(@RequestBody CreatePlaylistDto dto) {
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
    public ResponseEntity<Playlist> deletePlaylist(@PathVariable Long id) {
        if (playlistRepo.existsById(id)) {
            playlistService.deleteById(id);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list/{id}/song")
    public ResponseEntity<GetPlaylistSongDto> getSongsOfPlaylist(@PathVariable Long id) {
        return ResponseEntity.of(playlistRepo.findById(id)
                .map(playlist -> dtoConverter.of(playlist))
        );

    }

    @GetMapping("/list/{id1}/song/{id2}")
    public ResponseEntity<Song> getOneSongOfList(@PathVariable Long id1, @PathVariable Long id2) {
        return ResponseEntity.of(playlistRepo.findById(id1).get().getSongs()
                .stream()
                .filter(s -> s.getId().equals(id2))
                .findFirst());

    }

    @PostMapping("/list/{id1}/song/{id2}")
    public ResponseEntity<GetPlaylistSongDto> newSongToPlaylist(@PathVariable Long id1, @PathVariable Long id2) {
        playlistRepo.findById(id1).get().getSongs().add(songService.findById(id2).get());

        return ResponseEntity.of(playlistRepo.findById(id1)
                .map(playlist -> dtoConverter.of(playlist)));


    }

    @DeleteMapping("/list/{id1}/song/{id2}")
    public ResponseEntity<Playlist> deleteSongOfPlaylist(@PathVariable Long id1, @PathVariable Long id2){
        if(playlistRepo.existsById(id1)){
            playlistRepo.findById(id1).get().getSongs().remove(songRepo.findById(id2));
            playlistRepo.save(playlistService.findById(id1).get());
        }
            return ResponseEntity.noContent().build();


    }


}
