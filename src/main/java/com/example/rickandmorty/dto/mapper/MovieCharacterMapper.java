package com.example.rickandmorty.dto.mapper;

import com.example.rickandmorty.dto.CharacterResponseDto;
import com.example.rickandmorty.dto.external.ApiCharacterDto;
import com.example.rickandmorty.model.Gender;
import com.example.rickandmorty.model.MovieCharacter;
import com.example.rickandmorty.model.Status;
import org.springframework.stereotype.Component;

@Component
public class MovieCharacterMapper {
    public MovieCharacter toModel(ApiCharacterDto dto) {
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setExternalId(dto.getId());
        movieCharacter.setName(dto.getName());
        movieCharacter.setSpecies(dto.getSpecies());
        movieCharacter.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));
        movieCharacter.setGender(Gender.valueOf(dto.getGender().toUpperCase()));
        movieCharacter.setType(dto.getType());
        movieCharacter.setImage(dto.getImage());
        movieCharacter.setCreated(dto.getCreated());

        return movieCharacter;
    }

    public CharacterResponseDto toDto(MovieCharacter movieCharacter) {
        CharacterResponseDto dto = new CharacterResponseDto();
        dto.setId(movieCharacter.getId());
        dto.setExternalId(movieCharacter.getExternalId());
        dto.setName(movieCharacter.getName());
        dto.setSpecies(movieCharacter.getSpecies());
        dto.setStatus(movieCharacter.getStatus());
        dto.setGender(movieCharacter.getGender());
        dto.setType(movieCharacter.getType());
        dto.setImage(movieCharacter.getImage());
        dto.setCreated(movieCharacter.getCreated());

        return dto;
    }
}
