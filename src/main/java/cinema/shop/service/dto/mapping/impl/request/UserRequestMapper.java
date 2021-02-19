package cinema.shop.service.dto.mapping.impl.request;

import cinema.shop.model.User;
import cinema.shop.model.dto.request.UserRequestDto;
import cinema.shop.service.RoleService;
import cinema.shop.service.dto.mapping.DtoRequestMapper;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper implements DtoRequestMapper<UserRequestDto, User> {
    private final RoleService roleService;

    public UserRequestMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public User fromDto(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRoles(dto.getRoles().stream()
                .map(roleService::getRoleByName)
                .collect(Collectors.toList()));
        return user;
    }
}
