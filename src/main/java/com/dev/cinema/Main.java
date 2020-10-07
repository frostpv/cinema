package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(14);
        cinemaHall.setDescription("Green zone");
        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll();
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(2020, 10, 6, 10, 50));
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(1L, LocalDate.of(2020, 10, 6));
        movieSessionService.findAvailableSessions(1L, LocalDate.now().plusMonths(1));
        UserService userService = (UserService) Injector.getInstance("com.dev.cinema")
                .getInstance(UserService.class);
        AuthenticationService authenticationService =
                (AuthenticationService) Injector.getInstance("com.dev.cinema")
                        .getInstance(AuthenticationService.class);
        User user = new User("frostpv@gmail.com", "123");
        try {
            System.out.println(authenticationService.register(user.getEmail(), user.getPassword()));
        } catch (AuthenticationException e) {
            System.out.println(e);
        }
        try {
            System.out.println(authenticationService.login(user.getEmail(), user.getPassword()));
        } catch (AuthenticationException e) {
            System.out.println(e);
        }
        User user2 = new User("frostpv@gmail.com", "123");
        try {
            System.out.println(authenticationService.register(user2.getEmail(), user2.getPassword()));
        } catch (AuthenticationException e) {
            System.out.println(e);
        }

    }
}
