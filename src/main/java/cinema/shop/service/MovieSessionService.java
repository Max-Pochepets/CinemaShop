package cinema.shop.service;

import cinema.shop.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession movieSession);

    MovieSession update(MovieSession movieSession);

    boolean remove(MovieSession movieSession);
}
