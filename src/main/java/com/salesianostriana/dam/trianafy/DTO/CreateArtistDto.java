package com.salesianostriana.dam.trianafy.DTO;

import com.salesianostriana.dam.trianafy.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateArtistDto {

    @NotEmpty(message = "{createArtistDto.name.notempty}")
    private String name;

    public static Artist toArtist(CreateArtistDto dto){
        return Artist
                .builder()
                .name(dto.getName())
                .build();
    }
}
