package cinema.shop.service.dto.mapping.impl.request;

import cinema.shop.model.User;
import cinema.shop.model.dto.request.UserRequestDto;
import cinema.shop.service.dto.mapping.DtoRequestMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper implements DtoRequestMapper<UserRequestDto, User> {
    @Override
    public User fromDto(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }
}
