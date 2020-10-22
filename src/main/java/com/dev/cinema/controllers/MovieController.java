package com.dev.cinema.controllers;

import com.dev.cinema.dto.movie.MovieRequestDto;
import com.dev.cinema.dto.movie.MovieResponseDto;
import com.dev.cinema.mapper.MovieMapper;
import com.dev.cinema.service.MovieService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping("/")
    public void addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.add(movieMapper.dtoToMovie(movieRequestDto));
    }

    @GetMapping("/{movieId}")
    public MovieResponseDto getMovie(@PathVariable Long movieId) {
        return movieMapper.movieToDto(movieService.getById(movieId));
    }
}
