package com.example.moviedemo.domain.service;

import com.example.moviedemo.adapter.repository.MovieDataRepository;
import com.example.moviedemo.common.Exception.RecordNotFoundException;
import com.example.moviedemo.domain.model.dao.Movie;
import com.example.moviedemo.domain.model.dto.MovieDataRequest;
import com.example.moviedemo.domain.model.dto.MovieDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.example.moviedemo.common.Exception.ExceptionConstants.NO_RECORD_FOUND;

@Service
public class MovieDataServiceImpl implements MovieDataService {

    @Autowired
    MovieDataRepository movieDataRepository;

    @Transactional
    @Override
    public MovieDataResponse saveMovieData(final MovieDataRequest movieDataRequest) {
        Movie movie = new Movie.MovieDataBuilder().title(movieDataRequest.getTitle()).category(movieDataRequest.getCategory())
                .rating(movieDataRequest.getRating()).build();

        Movie savedMovie = movieDataRepository.save(movie);
        return transformMovieObject(savedMovie);
    }

    @Override
    public MovieDataResponse getMovieDataById(int id) {
        Movie movie = movieDataRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(NO_RECORD_FOUND));

        return transformMovieObject(movie);
    }

    @Override
    public List<MovieDataResponse> getAllMovieData() {
        List<Movie> movieList = movieDataRepository.findAll();
        if (movieList != null && movieList.isEmpty())
            throw new RecordNotFoundException(NO_RECORD_FOUND);

        List<MovieDataResponse> movieDataResponseList = new ArrayList<>();

        for (Movie movie : movieList) {
            MovieDataResponse movieDataResponse = transformMovieObject(movie);
            movieDataResponseList.add(movieDataResponse);
        }
        return movieDataResponseList;
    }

    @Transactional
    @Override
    public MovieDataResponse updateMovieData(final MovieDataRequest movieDataRequest) {
        Movie movie = movieDataRepository.findById(movieDataRequest.getId())
                .orElseThrow(() -> new RecordNotFoundException(NO_RECORD_FOUND));
        movie.updateMovie(movieDataRequest);

        return transformMovieObject(movie);
    }

    @Override
    public void deleteMovieData(int id) {
        movieDataRepository.deleteById(id);
    }

    @Override
    public MovieDataResponse transformMovieObject(Movie movie) {
        return MovieDataResponse.of(movie.id(), movie.title(), movie.category(), movie.rating());
    }
}
