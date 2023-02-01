package com.salesianostriana.dam.trianafy.DTO;

import com.salesianostriana.dam.trianafy.model.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSongDto {

    @NotEmpty
    private String title;
    private String album;
    @NotEmpty
    private String year;
    private Long artistId;

    public static Song of(CreateSongDto c){
        return new Song(
                c.getTitle(),
                c.getAlbum(),
                c.getYear()
        );
    }
}
