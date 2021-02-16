package cinema.shop.service.dto.mapping.impl.request;

import cinema.shop.model.CinemaHall;
import cinema.shop.model.dto.request.CinemaHallRequestDto;
import cinema.shop.service.dto.mapping.DtoRequestMapper;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallRequestMapper implements DtoRequestMapper<CinemaHallRequestDto, CinemaHall> {
    @Override
    public CinemaHall fromDto(CinemaHallRequestDto dto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(dto.getCapacity());
        cinemaHall.setDescription(dto.getDescription());
        return cinemaHall;
    }
}
