package cinema.shop.security;

import static cinema.shop.util.HashUtil.hashPassword;

import cinema.shop.exception.AuthenticationException;
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
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userByEmail = userService.findByEmail(email);
        if (userByEmail.isEmpty()
                || !hashPassword(password, userByEmail.get().getSalt())
                    .equals(userByEmail.get().getPassword())) {
            throw new AuthenticationException("Wrong credentials. Please try again.");
        }
        return userByEmail.get();
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
