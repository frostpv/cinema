package com.dev.cinema.service;

import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.model.CinemaHall;
import java.util.List;

public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
