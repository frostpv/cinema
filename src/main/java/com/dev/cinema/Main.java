package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Injector;
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

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);
    private static Injector injector = Injector.getInstance("com.dev.cinema");
    private static CinemaHallService cinemaHallService =
            (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static MovieSessionService movieSessionService =
            (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static UserService userService = (UserService) Injector.getInstance("com.dev.cinema")
            .getInstance(UserService.class);
    private static AuthenticationService authenticationService =
            (AuthenticationService) Injector.getInstance("com.dev.cinema")
                    .getInstance(AuthenticationService.class);
    private static ShoppingCartService shoppingCartService =
            (ShoppingCartService) Injector.getInstance("com.dev.cinema")
                    .getInstance(ShoppingCartService.class);
    private static OrderService orderService =
            (OrderService) Injector.getInstance("com.dev.cinema")
                    .getInstance(OrderService.class);

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(14);
        cinemaHall.setDescription("Green zone");
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll();
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.of(2020, 10, 6, 10, 50));
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(1L, LocalDate.of(2020, 10, 6));
        movieSessionService.findAvailableSessions(1L, LocalDate.now().plusMonths(1));
        User user = new User("frostpv@gmail.com", "123");
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
        user = userService.findByEmail("frostpv@gmail.com").get();
        shoppingCartService.addSession(movieSession, user);
        MovieSession movieSession1 = new MovieSession();
        movieSession1.setShowTime(LocalDateTime.now());
        movieSession1.setMovie(movie);
        movieSession1.setCinemaHall(cinemaHall);
        movieSessionService.add(movieSession1);
        shoppingCartService.addSession(movieSession1, user);
        logger.info("Get user shopping cart: " + shoppingCartService.getByUser(user));
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        orderService.completeOrder(shoppingCart.getTickets(), user);
        logger.info("Check user shopping cart should be empty: "
                + shoppingCartService.getByUser(user).getTickets());
        logger.info("Show user order list: " + orderService.getOrderHistory(user));
    }
}
