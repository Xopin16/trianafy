package com.salesianostriana.dam.trianafy.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePlaylistDto {

    @NotEmpty
    private String name;
    private String description;
}
