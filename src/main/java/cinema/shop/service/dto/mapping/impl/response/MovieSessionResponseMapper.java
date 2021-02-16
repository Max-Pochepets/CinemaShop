package cinema.shop.service.dto.mapping.impl.response;

import cinema.shop.model.MovieSession;
import cinema.shop.model.dto.response.MovieSessionResponseDto;
import cinema.shop.service.dto.mapping.DtoResponseMapper;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionResponseMapper implements DtoResponseMapper<MovieSessionResponseDto,
                                                        MovieSession> {
    @Override
    public MovieSessionResponseDto toDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setId(movieSession.getId());
        movieSessionResponseDto.setMovieTitle(movieSession.getMovie().getTitle());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime()
                                            .format(DateTimeFormatter
                                                    .ofPattern("dd.MM.yyyy HH:mm")));
        movieSessionResponseDto.setCinemaHallCapacity(movieSession.getCinemaHall().getCapacity());
        return movieSessionResponseDto;
    }
}
