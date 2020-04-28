package com.example.moviedemo.domain.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class MovieDataRequest {

    private int id;
    private String title;
    private String category;
    private double rating;

    public static MovieDataRequest of(final int id, final String title, final String category, final double rating) {
        MovieDataRequest movieDataRequest = new MovieDataRequest();
        movieDataRequest.id = id;
        movieDataRequest.title = title;
        movieDataRequest.category = category;
        movieDataRequest.rating = rating;

        return movieDataRequest;
    }

}
