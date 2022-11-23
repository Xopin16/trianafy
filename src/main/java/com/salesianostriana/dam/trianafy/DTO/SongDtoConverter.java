package com.salesianostriana.dam.trianafy.DTO;

import com.salesianostriana.dam.trianafy.model.Song;
import org.springframework.stereotype.Component;

@Component
public class SongDtoConverter {

    public Song SongDtoToSong(CreateSongDto c){
        return new Song(
                c.getTitle(),
                c.getAlbum(),
                c.getYear()
        );
    }

    public SongDto songToSongDto (Song song){
        return SongDto
                .builder()
                .title(song.getTitle())
                .album(song.getAlbum())
                .artist(song.getArtist().getName())
                .year(song.getYear())
                .build();
    }
}
