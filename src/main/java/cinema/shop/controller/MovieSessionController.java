package cinema.shop.controller;

import cinema.shop.model.MovieSession;
import cinema.shop.model.dto.request.MovieSessionRequestDto;
import cinema.shop.model.dto.response.MovieSessionResponseDto;
import cinema.shop.service.MovieSessionService;
import cinema.shop.service.dto.mapping.DtoRequestMapper;
import cinema.shop.service.dto.mapping.DtoResponseMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final DtoResponseMapper<MovieSessionResponseDto,
                    MovieSession> responseMapper;
    private final DtoRequestMapper<MovieSessionRequestDto,
                    MovieSession> requestMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  DtoResponseMapper<MovieSessionResponseDto,
                                          MovieSession> responseMapper,
                                  DtoRequestMapper<MovieSessionRequestDto,
                                          MovieSession> requestMapper) {
        this.movieSessionService = movieSessionService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    @PostMapping
    public void add(@RequestBody @Valid MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = requestMapper.fromDto(movieSessionRequestDto);
        movieSessionService.add(movieSession);
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> findAvailable(@RequestParam Long movieId,
                                                       @RequestParam
                                                       @DateTimeFormat(pattern = "dd.MM.yyyy")
                                                               LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = requestMapper.fromDto(movieSessionRequestDto);
        movieSession.setId(id);
        movieSessionService.update(movieSession);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        movieSessionService.remove(id);
    }
}
