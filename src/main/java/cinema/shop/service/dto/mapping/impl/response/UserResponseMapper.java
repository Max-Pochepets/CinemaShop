package cinema.shop.service.dto.mapping.impl.response;

import cinema.shop.model.User;
import cinema.shop.model.dto.response.UserResponseDto;
import cinema.shop.service.dto.mapping.DtoResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper implements DtoResponseMapper<UserResponseDto, User> {
    @Override
    public UserResponseDto toDto(User object) {
        UserResponseDto responseDto = new UserResponseDto();

        responseDto.setId(object.getId());
        responseDto.setEmail(object.getEmail());

        return responseDto;
    }
}
