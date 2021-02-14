package cinema.shop.controller;

import cinema.shop.model.User;
import cinema.shop.model.dto.request.UserRequestDto;
import cinema.shop.security.authentication.AuthenticationService;
import cinema.shop.service.dto.mapping.DtoRequestMapper;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final DtoRequestMapper<UserRequestDto, User> requestMapper;

    public AuthenticationController(AuthenticationService authenticationService,
                                    DtoRequestMapper<UserRequestDto, User> requestMapper) {
        this.authenticationService = authenticationService;
        this.requestMapper = requestMapper;
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserRequestDto requestDto) {
        User user = requestMapper.fromDto(requestDto);
        authenticationService.register(user);
    }
}
