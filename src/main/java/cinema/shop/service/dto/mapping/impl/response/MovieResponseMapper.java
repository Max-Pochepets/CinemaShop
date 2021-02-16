package cinema.shop.service.dto.mapping.impl.response;

import cinema.shop.model.Movie;
import cinema.shop.model.dto.response.MovieResponseDto;
import cinema.shop.service.dto.mapping.DtoResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class MovieResponseMapper implements DtoResponseMapper<MovieResponseDto, Movie> {
    @Override
    public MovieResponseDto toDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setId(movie.getId());
        movieResponseDto.setTitle(movie.getTitle());
        movieResponseDto.setDescription(movie.getDescription());
        return movieResponseDto;
    }
}
