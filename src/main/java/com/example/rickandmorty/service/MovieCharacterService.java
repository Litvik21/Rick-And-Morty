package com.example.rickandmorty.service;

import java.util.List;
import com.example.rickandmorty.model.MovieCharacter;

public interface MovieCharacterService {
    void syncExternalCharacters();

    MovieCharacter getRandomCharacter();

    List<MovieCharacter> findAllByNameContains(String namePart);

}
