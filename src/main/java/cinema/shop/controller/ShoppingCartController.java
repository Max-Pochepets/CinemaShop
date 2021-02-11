package cinema.shop.controller;

import cinema.shop.model.MovieSession;
import cinema.shop.model.ShoppingCart;
import cinema.shop.model.User;
import cinema.shop.model.dto.response.ShoppingCartResponseDto;
import cinema.shop.service.MovieSessionService;
import cinema.shop.service.ShoppingCartService;
import cinema.shop.service.UserService;
import cinema.shop.service.dto.mapping.DtoResponseMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieSessionService movieSessionService;
    private final DtoResponseMapper<ShoppingCartResponseDto, ShoppingCart> responseMapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService,
                                  DtoResponseMapper<ShoppingCartResponseDto,
                                          ShoppingCart> responseMapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.responseMapper = responseMapper;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(Authentication authentication, @RequestParam Long movieSessionId) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email).orElseThrow(()
                -> new RuntimeException("There is no such user with email " + email + "."));
        MovieSession movieSession = movieSessionService.get(movieSessionId);
        shoppingCartService.addSession(movieSession, user);
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email).orElseThrow(()
                -> new RuntimeException("There is no such user with email " + email + "."));
        return responseMapper.toDto(shoppingCartService.getByUser(user));
    }
}
