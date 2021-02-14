package cinema.shop.security.authentication;

import cinema.shop.model.User;
import cinema.shop.service.RoleService;
import cinema.shop.service.ShoppingCartService;
import cinema.shop.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleService = roleService;
    }

    @Override
    public User register(User user) {
        User userFromDB = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDB);
        return userFromDB;
    }
}
