package cinema.shop.service.dto.mapping.impl.request;

import cinema.shop.model.Movie;
import cinema.shop.model.dto.request.MovieRequestDto;
import cinema.shop.service.dto.mapping.DtoRequestMapper;
import org.springframework.stereotype.Component;

@Component
public class MovieRequestMapper implements DtoRequestMapper<MovieRequestDto, Movie> {
    @Override
    public Movie fromDto(MovieRequestDto dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        return movie;
    }
}
