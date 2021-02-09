package cinema.shop.controller;

import cinema.shop.model.dto.request.MovieSessionRequestDto;
import cinema.shop.model.dto.response.MovieSessionResponseDto;
import cinema.shop.service.MovieSessionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/cinema-hall")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;

    public MovieSessionController(MovieSessionService movieSessionService) {
        this.movieSessionService = movieSessionService;
    }

    @PostMapping
    public void add(MovieSessionRequestDto movieRequestDto) {

    }

    @GetMapping("/available")
    public MovieSessionResponseDto findAvailable(@RequestParam(name = "movieId") Long id,
                                                 @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        movieSessionService.findAvailableSessions(id, date).stream().map()
    }
}
