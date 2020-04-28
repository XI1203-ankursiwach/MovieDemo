package com.example.moviedemo.domain.model.dao;

import com.example.moviedemo.domain.model.dto.MovieDataRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.persistence.*;

import static com.example.moviedemo.common.PersistenceConstant.TableMetaData.MOVIE_DATA_TABLE;

@Entity
@Getter
@Accessors(chain = true, fluent = true)
@EqualsAndHashCode
@Table(name = MOVIE_DATA_TABLE)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 100)
    private int id;

    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    @Column(name = "CATEGORY", nullable = false, length = 100)
    private String category;

    @Column(name = "RATING", nullable = false, length = 10)
    private double rating;

    public void updateMovie(final MovieDataRequest movieDataRequest) {
        this.title = movieDataRequest.getTitle();
        this.category = movieDataRequest.getCategory();
        this.rating = movieDataRequest.getRating();
    }

    public static class MovieDataBuilder {
        private int id;
        private String title;
        private String category;
        private double rating;

        public MovieDataBuilder() {
        }

        public MovieDataBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public MovieDataBuilder category(final String category) {
            this.category = category;
            return this;
        }

        public MovieDataBuilder rating(final double rating) {
            this.rating = rating;
            return this;
        }

        //Return the finally Movie object
        public Movie build() {
            Movie movie = new Movie();
            movie.title = this.title;
            movie.category = this.category;
            movie.rating = this.rating;

            return movie;
        }
    }
}
