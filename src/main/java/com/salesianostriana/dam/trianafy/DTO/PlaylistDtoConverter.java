package com.salesianostriana.dam.trianafy.DTO;

import com.salesianostriana.dam.trianafy.model.Playlist;
import com.salesianostriana.dam.trianafy.model.Song;
import org.springframework.stereotype.Component;

@Component
public class PlaylistDtoConverter {

    public Playlist CreatePlaylistDtoToPlaylist(CreatePlaylistDto c){
        return new Playlist(
                c.getName(),
                c.getDescription()
        );
    }

    public CreatePlaylistDto playlistToPlaylistDto (Playlist playlist){
        return CreatePlaylistDto
                .builder()
                .name(playlist.getName())
                .description(playlist.getDescription())
                .build();
    }
}
