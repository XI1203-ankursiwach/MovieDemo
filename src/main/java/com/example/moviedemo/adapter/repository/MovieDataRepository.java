package com.example.moviedemo.adapter.repository;

import com.example.moviedemo.domain.model.dao.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieDataRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findById(final int id);

    List<Movie> findAll();

    void deleteById(final int id);
}
