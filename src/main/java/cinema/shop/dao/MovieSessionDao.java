package cinema.shop.dao;

import cinema.shop.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionDao {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession movieSession);

    MovieSession update(MovieSession movieSession);

    boolean remove(MovieSession movieSession);
}
