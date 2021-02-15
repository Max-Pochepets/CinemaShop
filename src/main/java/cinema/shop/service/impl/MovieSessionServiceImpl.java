package cinema.shop.service.impl;

import cinema.shop.dao.MovieSessionDao;
import cinema.shop.model.MovieSession;
import cinema.shop.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionDao movieSessionDao;

    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }

    @Override
    public MovieSession get(Long id) {
        Optional<MovieSession> optionalMovieSession = movieSessionDao.get(id);
        if (optionalMovieSession.isEmpty()) {
            return null;
        }
        return optionalMovieSession.get();
    }

    @Override
    public MovieSession update(MovieSession movieSession) {
        return movieSessionDao.update(movieSession);
    }

    @Override
    public boolean remove(Long id) {
        return movieSessionDao.remove(id);
    }
}
