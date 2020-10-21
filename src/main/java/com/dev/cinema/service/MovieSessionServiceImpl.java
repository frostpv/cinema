package com.dev.cinema.service;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.model.MovieSession;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private MovieSessionDao movieSessionDao;

    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession session) {
        return movieSessionDao.add(session);
    }
}
