package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = context.getBean(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(14);
        cinemaHall.setDescription("Green zone");
        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll();
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(2020, 10, 6, 10, 50));
        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(1L, LocalDate.of(2020, 10, 6));
        movieSessionService.findAvailableSessions(1L, LocalDate.now().plusMonths(1));
        User user = new User("frostpv@gmail.com", "123");
        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        try {
            logger.info("Register user with: "
                    + authenticationService.register(user.getEmail(), user.getPassword()));
        } catch (AuthenticationException e) {
            logger.error("Register error" + e);
        }
        try {
            logger.info("Authentication user with: "
                    + authenticationService.login(user.getEmail(), user.getPassword()));
        } catch (AuthenticationException e) {
            logger.info("Authentication error: " + e);
        }
        UserService userService = context.getBean(UserService.class);
        user = userService.findByEmail("frostpv@gmail.com").get();
        ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession, user);
        MovieSession movieSession1 = new MovieSession();
        movieSession1.setShowTime(LocalDateTime.now());
        movieSession1.setMovie(movie);
        movieSession1.setCinemaHall(cinemaHall);
        movieSessionService.add(movieSession1);
        shoppingCartService.addSession(movieSession1, user);
        logger.info("Get user shopping cart: " + shoppingCartService.getByUser(user));
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        OrderService orderService = context.getBean(OrderService.class);
        orderService.completeOrder(shoppingCart.getTickets(), user);
        logger.info("Check user shopping cart should be empty: "
                + shoppingCartService.getByUser(user).getTickets());
        logger.info("Show user order list: " + orderService.getOrderHistory(user));
    }
}
