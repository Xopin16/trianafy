package com.salesianostriana.dam.trianafy.controller;

import com.salesianostriana.dam.trianafy.DTO.PlaylistDto;
import com.salesianostriana.dam.trianafy.model.Playlist;
import com.salesianostriana.dam.trianafy.model.Song;
import com.salesianostriana.dam.trianafy.repos.PlaylistRepository;
import com.salesianostriana.dam.trianafy.service.PlaylistService;
import com.salesianostriana.dam.trianafy.service.SongService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistRepository playlistRepo;
    private final PlaylistService playlistService;

//    private final SongService songService;

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
    public ResponseEntity<Playlist> findOne(@PathVariable Long id){
//        List<Song> songs = songService.findAll();

        return ResponseEntity.of(playlistService.findById(id));

    }

//    @PostMapping ("/list/i{d}")
//    public ResponseEntity<Playlist> newPlaylist(@RequestBody Playlist playlist, @PathVariable Long id){
//
////        Añade una nueva lista de reproducción.
////        Si se añade satisfactoriamente, devuelve 201 Created
////        Si hay algún error en los datos que nos envían, debe devolver 400 Bad Request
////        Para la creación, necesitarás un DTO que debes diseñar tú. Las canciones de la playlist no se añaden en la creación.
////                El modelo de la respuesta debe ser:
////        { “id”: 1, “name”: “The name”, “description”: “The desc” }
//        return ResponseEntity.of(playlistService.findById(id).map(p->{
//           p.
//        }))
//    }

}
