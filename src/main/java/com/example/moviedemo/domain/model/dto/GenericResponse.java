package com.example.moviedemo.domain.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GenericResponse {

    private String message;

    public static GenericResponse of(final String message) {
        GenericResponse genericResponse = new GenericResponse(message);

        return genericResponse;
    }
}
