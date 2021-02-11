package cinema.shop.controller;

import cinema.shop.model.dto.request.*;
import cinema.shop.security.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
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
