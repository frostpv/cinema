package com.dev.cinema.mapper;

import com.dev.cinema.dto.movie.MovieRequestDto;
import com.dev.cinema.dto.movie.MovieResponseDto;
import com.dev.cinema.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie dtoToMovie(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movie;
    }

    public MovieResponseDto MovieToDto(Movie movie) {
        return new MovieResponseDto(movie.getId(),movie.getTitle(),movie.getDescription());
    }
}
