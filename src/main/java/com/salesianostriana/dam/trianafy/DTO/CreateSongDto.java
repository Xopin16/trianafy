package com.salesianostriana.dam.trianafy.DTO;

import com.salesianostriana.dam.trianafy.model.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSongDto {

    @NotEmpty(message = "{createSongDto.title.notempty}")
    private String title;
    private String album;
    @NotEmpty(message = "{createSongDto.year.notempty}")
    private String year;
    private Long artistId;

    public static Song toSong(CreateSongDto c){
        return new Song(
                c.getTitle(),
                c.getAlbum(),
                c.getYear()
        );
    }
}
