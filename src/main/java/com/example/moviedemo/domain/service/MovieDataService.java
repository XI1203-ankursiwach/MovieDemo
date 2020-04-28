package com.example.moviedemo.domain.service;

import com.example.moviedemo.domain.model.dao.Movie;
import com.example.moviedemo.domain.model.dto.MovieDataRequest;
import com.example.moviedemo.domain.model.dto.MovieDataResponse;

import java.util.List;

public interface MovieDataService {

    MovieDataResponse saveMovieData(final MovieDataRequest movieDataRequest);

    MovieDataResponse getMovieDataById(final int id);

    List<MovieDataResponse> getAllMovieData();

    MovieDataResponse updateMovieData(final MovieDataRequest movieDataRequest);

    void deleteMovieData(final int id);

    MovieDataResponse transformMovieObject(final Movie movie);

}
