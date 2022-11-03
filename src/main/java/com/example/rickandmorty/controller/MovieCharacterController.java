package com.example.rickandmorty.controller;

import java.util.List;
import java.util.stream.Collectors;
import com.example.rickandmorty.dto.CharacterResponseDto;
import com.example.rickandmorty.dto.mapper.MovieCharacterMapper;
import com.example.rickandmorty.model.MovieCharacter;
import com.example.rickandmorty.service.MovieCharacterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-character")
public class MovieCharacterController {
    private final MovieCharacterService characterService;
    private final MovieCharacterMapper mapper;

    public MovieCharacterController(MovieCharacterService characterService,
                                    MovieCharacterMapper mapper) {
        this.characterService = characterService;
        this.mapper = mapper;
    }

    @GetMapping("/random")
    @ApiOperation(value = "get random movie character")
    public CharacterResponseDto getRandom() {
        MovieCharacter randomCharacter = characterService.getRandomCharacter();
        return mapper.toDto(randomCharacter);
    }

    @GetMapping("/by-name")
    @ApiOperation(value = "get movie character or list of characters "
            + " by part of name")
    public List<CharacterResponseDto> findAllByName(@RequestParam("name")
                                                        @ApiParam(value = "enter part of name")
                                                        String namePart) {
        List<MovieCharacter> allByNameContains = characterService.findAllByNameContains(namePart);
        return allByNameContains.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
