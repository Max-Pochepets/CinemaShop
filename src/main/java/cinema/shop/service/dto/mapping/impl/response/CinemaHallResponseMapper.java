package cinema.shop.service.dto.mapping.impl.response;

import cinema.shop.model.CinemaHall;
import cinema.shop.model.dto.response.CinemaHallResponseDto;
import cinema.shop.service.dto.mapping.DtoMapper;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallResponseMapper implements DtoMapper<CinemaHallResponseDto, CinemaHall> {
    @Override
    public CinemaHallResponseDto toDto(CinemaHall cinemaHall) {
        return null;
    }

    @Override
    public CinemaHall fromDto(CinemaHallResponseDto dto) {
        return null;
    }
}
