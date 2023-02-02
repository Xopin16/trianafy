package com.salesianostriana.dam.trianafy.service;


import com.salesianostriana.dam.trianafy.DTO.CreatePlaylistDto;
import com.salesianostriana.dam.trianafy.DTO.PlaylistDtoConverter;
import com.salesianostriana.dam.trianafy.exception.PlaylistNotFoundException;
import com.salesianostriana.dam.trianafy.model.Playlist;
import com.salesianostriana.dam.trianafy.model.Song;
import com.salesianostriana.dam.trianafy.repos.PlaylistRepository;
import com.salesianostriana.dam.trianafy.repos.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository repository;

    private final PlaylistDtoConverter converter;

    public Playlist add(Playlist playlist) {
        return repository.save(playlist);
    }

    public Playlist save(CreatePlaylistDto playlistDto) {
        return repository.save(converter.CreatePlaylistDtoToPlaylist(playlistDto));
    }

    public Optional<Playlist> findById(Long id) {
        return repository.findById(id);
    }

    public Playlist findByIdPlaylist(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new PlaylistNotFoundException(id));
    }

    public List<Playlist> findAll() {
        return repository.findAll();
    }

    public List<Playlist> findAllPlaylist() {
        List<Playlist> result = repository.findAll();
        if(result.isEmpty()){
            throw new PlaylistNotFoundException();
        }
        return result;
    }

    public Playlist edit(Playlist playlist) {
        return repository.save(playlist);
    }

    public Playlist editPlaylist(Long id, CreatePlaylistDto playlistDto){
        return repository.findById(id)
                .map(playlist -> {
                    playlist.setName(playlistDto.getName());
                    playlist.setDescription(playlistDto.getDescription());
                    return repository.save(playlist);
                }).orElseThrow(PlaylistNotFoundException::new);
    }

    public void delete(Playlist playlist) {
        repository.delete(playlist);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
