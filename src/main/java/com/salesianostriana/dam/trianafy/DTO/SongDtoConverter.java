package com.salesianostriana.dam.trianafy.DTO;

import com.salesianostriana.dam.trianafy.model.Song;
import org.springframework.stereotype.Component;

@Component
public class SongDtoConverter {

    public Song CreateSongDtoToSong(CreateSongDto c){
        return new Song(
                c.getId(),
                c.getTitle(),
                c.getAlbum(),
                c.getYear()
        );
    }

    public SongDto songToSongDto (Song song){
        return SongDto
                .builder()
                .id(song.getId())
                .title(song.getTitle())
                .album(song.getAlbum())
                .artist(song.getArtist().getName())
                .year(song.getYear())
                .build();
    }
}
