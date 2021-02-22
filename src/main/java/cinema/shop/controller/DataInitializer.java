package cinema.shop.controller;

import cinema.shop.model.Role;
import cinema.shop.model.User;
import cinema.shop.security.authentication.AuthenticationService;
import cinema.shop.service.RoleService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AuthenticationService authenticationService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    public DataInitializer(AuthenticationService authenticationService, RoleService roleService,
                           PasswordEncoder encoder) {
        this.authenticationService = authenticationService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @PostConstruct
    private void inject() {
        Role admin = new Role();
        Role user = new Role();
        admin.setRoleName(Role.RoleType.ADMIN);
        user.setRoleName(Role.RoleType.USER);
        roleService.add(admin);
        roleService.add(user);

        User adminUser = new User();
        adminUser.setEmail("admin@hotmail.com");
        adminUser.setPassword(encoder.encode("1234"));
        adminUser.setRoles(List.of(admin, user));
        authenticationService.register(adminUser);
    }
}
