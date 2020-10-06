package com.dev.cinema.service;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession session) {
        return movieSessionDao.add(session);
    }
}