package cinema.shop.security;

import static cinema.shop.util.HashUtil.hashPassword;

import cinema.shop.lib.exception.AuthenticationException;
import cinema.shop.model.User;
import cinema.shop.service.ShoppingCartService;
import cinema.shop.service.UserService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User userFromDB = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDB);
        return userFromDB;
    }
}
