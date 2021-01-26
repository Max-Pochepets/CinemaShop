package cinema.shop;

import cinema.shop.lib.Injector;
import cinema.shop.model.Movie;
import cinema.shop.service.abstraction.MovieService;

public class App {
    private static Injector injector = Injector.getInstance("cinema.shop");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
    }
}
