package com.example.moviedemo.domain.service;

import com.example.moviedemo.adapter.repository.MovieDataRepository;
import com.example.moviedemo.domain.model.dao.Movie;
import com.example.moviedemo.domain.model.dto.MovieDataRequest;
import com.example.moviedemo.domain.model.dto.MovieDataResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MovieDataServiceImplTest {

    @InjectMocks
    private MovieDataServiceImpl movieDataService;

    @Mock
    private MovieDataRepository movieDataRepository;

    @Test
    public void saveMovieData() {
        MovieDataRequest movieDataRequest = MovieDataRequest.of(1, "xyz", "action", 4.0);
        Movie movie = new Movie.MovieDataBuilder().title(movieDataRequest.getTitle()).category(movieDataRequest.getCategory())
                .rating(movieDataRequest.getRating()).build();

        when(movieDataRepository.save(movie)).thenReturn(movie);

        MovieDataResponse movieDataResponse = movieDataService.saveMovieData(movieDataRequest);
        assertEquals(4, movieDataResponse.getRating(), 4);
    }

    @Test
    public void getMovieDataById() {
        Movie movie = new Movie.MovieDataBuilder().title("xyz").category("action")
                .rating(3.0).build();
        when(movieDataRepository.findById(1)).thenReturn(Optional.of(movie));

        MovieDataResponse movieDataResponse = movieDataService.getMovieDataById(1);

        assertEquals(3, movieDataResponse.getRating(), 3);
    }

    @Test
    public void getAllMovieData() {
        List<Movie> movieList = new ArrayList<>();
        Movie movie = new Movie.MovieDataBuilder().title("xyz").category("action")
                .rating(3.0).build();
        movieList.add(movie);

        when(movieDataRepository.findAll()).thenReturn(movieList);

        List<MovieDataResponse> movieDataResponse = movieDataService.getAllMovieData();

        assertEquals(movieDataResponse.size(), 1);
    }

    @Test
    public void updateMovieData() {
        MovieDataRequest movieDataRequest = MovieDataRequest.of(1, "xyz", "action", 4.0);
        Movie movie = new Movie.MovieDataBuilder().title(movieDataRequest.getTitle()).category(movieDataRequest.getCategory())
                .rating(movieDataRequest.getRating()).build();

        when(movieDataRepository.findById(movieDataRequest.getId())).thenReturn(Optional.of(movie));

        MovieDataResponse movieDataResponse = movieDataService.updateMovieData(movieDataRequest);

        assertEquals(movieDataResponse.getCategory(), "action");
    }

    @Test
    public void deleteMovieData() {
        doNothing().when(movieDataRepository).deleteById(1);
    }

}