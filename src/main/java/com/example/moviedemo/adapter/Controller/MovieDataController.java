package com.example.moviedemo.adapter.Controller;

import com.example.moviedemo.common.Exception.BadRequestException;
import com.example.moviedemo.common.Utils;
import com.example.moviedemo.domain.model.dto.GenericResponse;
import com.example.moviedemo.domain.model.dto.MovieDataRequest;
import com.example.moviedemo.domain.model.dto.MovieDataResponse;
import com.example.moviedemo.domain.service.MovieDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.moviedemo.common.CommonMessageConstants.DATA_DELETED_SUCCESSFULLY;
import static com.example.moviedemo.common.Exception.ExceptionConstants.INVALID_RATING_RANGE;
import static com.example.moviedemo.common.RestConstant.MOVIE_DATA_API;
import static com.example.moviedemo.common.RestConstant.MovieData.MOVIE_API;
import static com.example.moviedemo.common.RestConstant.MovieData.MOVIE_API_BY_ID;


@RestController
@RequestMapping(MOVIE_DATA_API)
public class MovieDataController {

    @Autowired
    MovieDataService movieDataService;

    @PostMapping(MOVIE_API)
    public ResponseEntity<MovieDataResponse> createMovieData(@RequestBody MovieDataRequest movieDataRequest) {

        //Validating the input rating range
        if (!Utils.checkRatingRange(movieDataRequest.getRating())) {
            throw new BadRequestException(INVALID_RATING_RANGE);
        }

        MovieDataResponse movieDataResponse = movieDataService.saveMovieData(movieDataRequest);

        return ResponseEntity.ok().body(movieDataResponse);
    }

    @GetMapping(MOVIE_API)
    public ResponseEntity<List<MovieDataResponse>> getAllMovieData() {

        List<MovieDataResponse> movieDataResponseList = movieDataService.getAllMovieData();

        return ResponseEntity.ok().body(movieDataResponseList);
    }

    @GetMapping(MOVIE_API_BY_ID)
    public ResponseEntity<MovieDataResponse> getMovieDataById(@PathVariable int id) {

        MovieDataResponse movieDataResponse = movieDataService.getMovieDataById(id);

        return ResponseEntity.ok().body(movieDataResponse);
    }

    @PutMapping(MOVIE_API)
    public ResponseEntity<MovieDataResponse> updateMovieData(@RequestBody MovieDataRequest movieDataRequest) {
        //Validating the input rating range
        if (!Utils.checkRatingRange(movieDataRequest.getRating())) {
            throw new BadRequestException(INVALID_RATING_RANGE);
        }

        MovieDataResponse movieDataResponse = movieDataService.updateMovieData(movieDataRequest);

        return ResponseEntity.ok().body(movieDataResponse);
    }

    @DeleteMapping(MOVIE_API_BY_ID)
    public ResponseEntity<GenericResponse> deleteMovieDataById(@PathVariable int id) {

        movieDataService.deleteMovieData(id);

        return ResponseEntity.ok().body(GenericResponse.of(DATA_DELETED_SUCCESSFULLY));
    }
}
