package cinema.shop.controller;

import cinema.shop.model.Movie;
import cinema.shop.model.dto.request.MovieRequestDto;
import cinema.shop.model.dto.response.MovieResponseDto;
import cinema.shop.service.MovieService;
import cinema.shop.service.dto.mapping.DtoRequestMapper;
import cinema.shop.service.dto.mapping.DtoResponseMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final DtoRequestMapper<MovieRequestDto, Movie> requestMapper;
    private final DtoResponseMapper<MovieResponseDto, Movie> responseMapper;

    public MovieController(MovieService movieService,
                           DtoRequestMapper<MovieRequestDto, Movie> requestMapper,
                           DtoResponseMapper<MovieResponseDto, Movie> responseMapper) {
        this.movieService = movieService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @PostMapping
    public void add(@RequestBody @Valid MovieRequestDto dto) {
        Movie movie = requestMapper.fromDto(dto);
        movieService.add(movie);
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll().stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }
}
