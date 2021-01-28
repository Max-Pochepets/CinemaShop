package cinema.shop.service.impl;

import cinema.shop.dao.MovieSessionDao;
import cinema.shop.lib.Inject;
import cinema.shop.lib.ServiceImpl;
import cinema.shop.model.MovieSession;
import cinema.shop.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;

@ServiceImpl
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }
}
