package com.example.rickandmorty.dto.external;

import lombok.Data;

@Data
public class ApiCharacterDto {
    private Long id;
    private String name;
    private String species;
    private String status;
    private String gender;
    private String type;
    private String image;
    private String created;
}
