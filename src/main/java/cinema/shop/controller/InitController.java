package cinema.shop.controller;

import cinema.shop.model.Role;
import cinema.shop.model.User;
import cinema.shop.security.authentication.AuthenticationService;
import cinema.shop.service.RoleService;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;

@Controller
public class InitController {
    private final AuthenticationService authenticationService;
    private final RoleService roleService;

    public InitController(AuthenticationService authenticationService, RoleService roleService) {
        this.authenticationService = authenticationService;
        this.roleService = roleService;
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
        adminUser.setPassword("12345");
        adminUser.setRole(admin);
        authenticationService.register(adminUser);
    }
}
