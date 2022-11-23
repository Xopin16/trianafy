package com.salesianostriana.dam.trianafy.DTO;

import com.salesianostriana.dam.trianafy.model.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SongDto {

    private String title, album;
    private long artistId;
    private int year;

    public static SongDto of (Song song){
        return SongDto
                .builder()
                .title(song.getTitle())
                .album(song.getAlbum())
                .artistId(song.getArtist().getId())
                .year(song.getYear())
                .build();
    }
}
