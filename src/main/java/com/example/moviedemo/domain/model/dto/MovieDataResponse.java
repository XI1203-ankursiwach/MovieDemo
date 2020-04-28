package com.example.moviedemo.domain.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieDataResponse {

    private int id;
    private String title;
    private String category;
    private double rating;

    public static MovieDataResponse of(final int id, final String title, final String category, final double rating) {
        MovieDataResponse movieDataResponse = new MovieDataResponse(id, title, category, rating);

        return movieDataResponse;
    }
}
