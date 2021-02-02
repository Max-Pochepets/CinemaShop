package cinema.shop;

import cinema.shop.lib.Injector;
import cinema.shop.lib.exception.AuthenticationException;
import cinema.shop.lib.exception.RegistrationException;
import cinema.shop.model.CinemaHall;
import cinema.shop.model.Movie;
import cinema.shop.model.MovieSession;
import cinema.shop.model.User;
import cinema.shop.security.AuthenticationService;
import cinema.shop.service.CinemaHallService;
import cinema.shop.service.MovieService;
import cinema.shop.service.MovieSessionService;
import cinema.shop.service.ShoppingCartService;

import java.time.LocalDateTime;

public class App {
    private static Injector injector = Injector.getInstance("cinema.shop");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService
                = (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(50);
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        LocalDateTime showTime
                = LocalDateTime.of(2020, 2, 14, 16, 30);
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(showTime);
        MovieSessionService movieSessionService
                = (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        System.out.println(movieSessionService
                .findAvailableSessions(movie.getId(), showTime.toLocalDate()));

        AuthenticationService authenticationService
                = (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User user = null;
        try {
            user = authenticationService.register("maksym.pochepets@gmail.com", "1234");
            authenticationService.register("maksym.pochepets@gmail.com", "1234");
        } catch (RegistrationException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("After login: "
                    + authenticationService.login("maksym.pochepets@gmail.com", "1234"));
            System.out.println("After login: "
                    + authenticationService.login("maksym.pochepets@gmail.com", "12345"));
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }

        ShoppingCartService shoppingCartService
                = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession, user);
        System.out.println(shoppingCartService.getByUser(user));
        shoppingCartService.clear(shoppingCartService.getByUser(user));
        System.out.println(shoppingCartService.getByUser(user));
    }
}
