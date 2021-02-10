package cinema.shop.service.impl;

import cinema.shop.dao.CinemaHallDao;
import cinema.shop.model.CinemaHall;
import cinema.shop.service.CinemaHallService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    private final CinemaHallDao cinemaHallDao;

    public CinemaHallServiceImpl(CinemaHallDao cinemaHallDao) {
        this.cinemaHallDao = cinemaHallDao;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public CinemaHall get(Long id) {
        Optional<CinemaHall> optionalCinemaHall = cinemaHallDao.get(id);
        if (optionalCinemaHall.isEmpty()) {
            return null;
        }
        return optionalCinemaHall.get();
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
