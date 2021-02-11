package cinema.shop.controller;

import cinema.shop.model.dto.request.UserRequestDto;
import cinema.shop.security.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserRequestDto requestDto) {
        authenticationService.register(requestDto.getEmail(), requestDto.getPassword());
    }
}
