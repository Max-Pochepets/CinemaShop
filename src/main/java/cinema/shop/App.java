package cinema.shop;

import cinema.shop.lib.Injector;
import cinema.shop.model.CinemaHall;
import cinema.shop.model.Movie;
import cinema.shop.model.MovieSession;
import cinema.shop.service.CinemaHallService;
import cinema.shop.service.MovieService;
import cinema.shop.service.MovieSessionService;
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

        MovieSessionService movieSessionService
                = (MovieSessionService) injector.getInstance(MovieSessionService.class);
        LocalDateTime showTime
                = LocalDateTime.of(2020, 2, 14, 16, 30);
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setShowTime(showTime);
        movieSessionService.add(movieSession);
        System.out.println(movieSessionService
                .findAvailableSessions(movie.getId(), showTime.toLocalDate()));
    }
}
