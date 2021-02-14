package cinema.shop.service.impl;

import cinema.shop.dao.MovieDao;
import cinema.shop.model.Movie;
import cinema.shop.service.MovieService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).orElseThrow(() -> new NoSuchElementException("There is no such movie."));
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
