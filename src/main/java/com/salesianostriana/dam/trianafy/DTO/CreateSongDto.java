package com.salesianostriana.dam.trianafy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSongDto {

    private String title;
    private String album;
    private int year;
    private Long artistId;
}
