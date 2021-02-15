package cinema.shop.service.dto.mapping.impl.response;

import cinema.shop.model.CinemaHall;
import cinema.shop.model.dto.response.CinemaHallResponseDto;
import cinema.shop.service.dto.mapping.DtoResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallResponseMapper implements DtoResponseMapper<CinemaHallResponseDto,
                                                        CinemaHall> {
    @Override
    public CinemaHallResponseDto toDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setId(cinemaHall.getId());
        cinemaHallResponseDto.setCapacity(cinemaHall.getCapacity());
        return cinemaHallResponseDto;
    }
}
