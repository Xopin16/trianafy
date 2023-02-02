package com.salesianostriana.dam.trianafy.service;


import com.salesianostriana.dam.trianafy.DTO.CreateSongDto;
import com.salesianostriana.dam.trianafy.exception.ArtistNotFoundException;
import com.salesianostriana.dam.trianafy.exception.SongNotFoundException;
import com.salesianostriana.dam.trianafy.model.Song;
import com.salesianostriana.dam.trianafy.repos.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository repository;

    public Song add(Song song) {
        return repository.save(song);
    }

    public Song save(CreateSongDto songDto) {
        return repository.save(CreateSongDto.toSong(songDto));
    }

    public Optional<Song> findById(Long id) {
        return repository.findById(id);
    }

    public Song findByIdSong(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new SongNotFoundException(id));
    }

    public List<Song> findAll() {
        return repository.findAll();
    }

    public List<Song> findAllSongs() {
        List<Song> result = repository.findAll();

        if(result.isEmpty()){
            throw new SongNotFoundException();
        }
        return result;
    }

    public Song edit(Song song) {
        return repository.save(song);
    }

    public Song editSong(Long id, CreateSongDto songDto){
        return repository.findById(id)
                .map(song -> {
                    song.setTitle(songDto.getTitle());
                    song.setAlbum(songDto.getAlbum());
                    song.setYear(songDto.getYear());
                    return repository.save(song);
                }).orElseThrow(()-> new ArtistNotFoundException(id));
    }

    public void delete(Song song) {
        repository.delete(song);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
