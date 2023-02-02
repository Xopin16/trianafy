package com.salesianostriana.dam.trianafy.service;


import com.salesianostriana.dam.trianafy.DTO.CreateArtistDto;
import com.salesianostriana.dam.trianafy.exception.ArtistNotFoundException;
import com.salesianostriana.dam.trianafy.model.Artist;
import com.salesianostriana.dam.trianafy.repos.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository repository;


    public Artist add(Artist artist) {
        return repository.save(artist);
    }

    public Optional<Artist> findById(Long id) {
        return repository.findById(id);
    }

    public Artist findByIdArtist(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException(id));
    }

    public Artist save(CreateArtistDto artistDto){
        return repository.save(CreateArtistDto.toArtist(artistDto));
    }

    public List<Artist> findAll() {
        return repository.findAll();
    }

    public List<Artist> findAllArtist() {
        List<Artist> result = repository.findAll();

        if (result.isEmpty())
            throw new ArtistNotFoundException();

        return result;
    }

    public Artist edit(Artist artist) {
        return repository.save(artist);
    }

    public Artist editArtist(Long id, CreateArtistDto artistDto){
        return repository.findById(id)
                .map(artist -> {
                    artist.setName(artistDto.getName());
                    return repository.save(artist);
                }).orElseThrow(ArtistNotFoundException::new);
    }
    public void delete(Artist artist) {
        repository.delete(artist);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
