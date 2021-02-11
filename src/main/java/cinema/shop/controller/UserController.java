package cinema.shop.controller;

import cinema.shop.model.User;
import cinema.shop.model.dto.response.UserResponseDto;
import cinema.shop.service.UserService;
import cinema.shop.service.dto.mapping.DtoResponseMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final DtoResponseMapper<UserResponseDto, User> responseMapper;

    public UserController(UserService userService,
                          DtoResponseMapper<UserResponseDto, User> responseMapper) {
        this.userService = userService;
        this.responseMapper = responseMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto findByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email).orElseThrow(()
                -> new RuntimeException("There is no such user with email " + email + "."));
        return responseMapper.toDto(user);
    }
}
