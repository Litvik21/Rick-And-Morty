package com.example.rickandmorty.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.example.rickandmorty.dto.external.ApiCharacterDto;
import com.example.rickandmorty.dto.external.ApiResponseDto;
import com.example.rickandmorty.dto.mapper.MovieCharacterMapper;
import com.example.rickandmorty.model.MovieCharacter;
import com.example.rickandmorty.repository.MovieCharacterRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MovieCharacterServiceImpl implements MovieCharacterService {
    @Value("${com.example.rickandmorty.key}")
    private String CHARACTER_LINK;
    private final HttpClient httpClient;
    private final MovieCharacterRepository movieCharacterRepository;
    private final MovieCharacterMapper mapper;

    public MovieCharacterServiceImpl(HttpClient httpClient,
                                     MovieCharacterRepository movieCharacterRepository, MovieCharacterMapper mapper) {
        this.httpClient = httpClient;
        this.movieCharacterRepository = movieCharacterRepository;
        this.mapper = mapper;
    }

    @Override
    public MovieCharacter getRandomCharacter() {
        long count = movieCharacterRepository.count();
        long randomId = (long) (Math.random() * count);

        MovieCharacter movieCharacter =
                movieCharacterRepository.getReferenceById(randomId);

        return movieCharacter;
    }

    @Override
    public List<MovieCharacter> findAllByNameContains(String namePart) {
        return movieCharacterRepository.findAllByNameContains(namePart);
    }

    @ApiOperation(value = "Fires every day at 8:00 o'clock")
    @Scheduled(cron = "* 0 8 * * *")
    @Override
    public void syncExternalCharacters() {
        ApiResponseDto apiResponseDto = httpClient
                .get(CHARACTER_LINK, ApiResponseDto.class);

        if (checkForNewData(apiResponseDto)) {
            saveDtosToDb(apiResponseDto);
        } else {
            update(apiResponseDto);
        }

        while (apiResponseDto.getInfo().getNext() != null) {
            apiResponseDto = httpClient
                    .get(apiResponseDto.getInfo().getNext(), ApiResponseDto.class);
            if (checkForNewData(apiResponseDto)) {
                saveDtosToDb(apiResponseDto);
            } else {
                update(apiResponseDto);
            }
        }
    }

    private void saveDtosToDb(ApiResponseDto dto) {
        Map<Long, ApiCharacterDto> externalDtos = Arrays.stream(dto.getResults())
                .collect(Collectors.toMap(ApiCharacterDto::getId, Function.identity()));

        Set<Long> externalIds = externalDtos.keySet();

        List<MovieCharacter> existingCharacters = movieCharacterRepository.findAllByExternalIdIn(externalIds);

        Map<Long, MovieCharacter> existingCharactersWithIds = existingCharacters.stream()
                .collect(Collectors.toMap(MovieCharacter::getExternalId, Function.identity()));

        Set<Long> existingIds = existingCharactersWithIds.keySet();

        externalIds.removeAll(existingIds);

        List<MovieCharacter> charactersToSave = externalIds
                .stream()
                .map(i -> mapper.toModel(externalDtos.get(i)))
                .collect(Collectors.toList());

        movieCharacterRepository.saveAll(charactersToSave);
    }

    private void update(ApiResponseDto dto) {
        List<MovieCharacter> externalCharacters =
                Arrays.stream(dto.getResults()).map(mapper::toModel).toList();

        for (MovieCharacter currentCharacter : externalCharacters) {
            MovieCharacter characterToUpdate =
                    movieCharacterRepository.findByExternalId(currentCharacter.getExternalId());
            characterToUpdate.setName(currentCharacter.getName());
            characterToUpdate.setStatus(currentCharacter.getStatus());
            characterToUpdate.setGender(currentCharacter.getGender());
            characterToUpdate.setSpecies(currentCharacter.getSpecies());
            characterToUpdate.setType(currentCharacter.getType());
            characterToUpdate.setImage(currentCharacter.getImage());
            characterToUpdate.setCreated(currentCharacter.getCreated());

            movieCharacterRepository.save(characterToUpdate);
        }
    }

    private boolean checkForNewData(ApiResponseDto dto) {
        long externalIdsCount = Arrays.stream(dto.getResults())
                .map(ApiCharacterDto::getId).count();

        long existingIdsCount = movieCharacterRepository.findAll()
                .stream().count();
        return existingIdsCount == externalIdsCount;
    }
}
