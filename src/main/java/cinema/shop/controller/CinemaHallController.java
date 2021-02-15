package cinema.shop.controller;

import cinema.shop.model.CinemaHall;
import cinema.shop.model.dto.request.CinemaHallRequestDto;
import cinema.shop.model.dto.response.CinemaHallResponseDto;
import cinema.shop.service.CinemaHallService;
import cinema.shop.service.dto.mapping.DtoRequestMapper;
import cinema.shop.service.dto.mapping.DtoResponseMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final DtoRequestMapper<CinemaHallRequestDto,
                    CinemaHall> requestMapper;
    private final DtoResponseMapper<CinemaHallResponseDto,
                    CinemaHall> responseMapper;

    public CinemaHallController(CinemaHallService cinemaHallService,
                                DtoRequestMapper<CinemaHallRequestDto,
                                        CinemaHall> requestMapper,
                                DtoResponseMapper<CinemaHallResponseDto,
                                        CinemaHall> responseMapper) {
        this.cinemaHallService = cinemaHallService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @PostMapping
    public void add(@RequestBody CinemaHallRequestDto requestDto) {
        CinemaHall cinemaHall = requestMapper.fromDto(requestDto);
        cinemaHallService.add(cinemaHall);
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService.getAll().stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }
}
