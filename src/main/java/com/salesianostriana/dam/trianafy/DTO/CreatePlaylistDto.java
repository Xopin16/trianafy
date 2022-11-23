package com.salesianostriana.dam.trianafy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaylistDto {

    private Long songId;
    private String name;
    private String description;
}
