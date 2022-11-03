package com.example.rickandmorty.repository;

import java.util.List;
import java.util.Set;
import com.example.rickandmorty.model.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Long> {
    List<MovieCharacter> findAllByExternalIdIn(Set<Long> externalIds);

    List<MovieCharacter> findAllByNameContains(String namePart);

    MovieCharacter findByExternalId(Long externalId);
}
