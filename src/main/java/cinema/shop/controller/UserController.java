package cinema.shop.controller;

import cinema.shop.model.User;
import cinema.shop.model.dto.response.UserResponseDto;
import cinema.shop.service.UserService;
import cinema.shop.service.dto.mapping.DtoResponseMapper;
import java.util.Optional;
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
        Optional<User> optionalUser = userService.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return null;
        }
        return responseMapper.toDto(optionalUser.get());
    }
}
