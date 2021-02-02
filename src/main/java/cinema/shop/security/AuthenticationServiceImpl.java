package cinema.shop.security;

import cinema.shop.lib.Inject;
import cinema.shop.lib.ServiceImpl;
import cinema.shop.lib.exception.AuthenticationException;
import cinema.shop.lib.exception.RegistrationException;
import cinema.shop.model.User;
import cinema.shop.service.ShoppingCartService;
import cinema.shop.service.UserService;
import cinema.shop.util.HashUtil;
import java.util.Optional;

@ServiceImpl
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userByEmail = userService.findByEmail(email);
        if (userByEmail.isEmpty()) {
            throw new AuthenticationException("Wrong credentials. Please try again.");
        }
        User user = userByEmail.get();
        byte[] salt = user.getSalt();
        if (!HashUtil.hashPassword(password, salt).equals(user.getPassword())) {
            throw new AuthenticationException("Wrong credentials. Please try again.");
        }
        return user;
    }

    @Override
    public User register(String email, String password) throws RegistrationException {
        if (userService.findByEmail(email).isPresent()) {
            throw new RegistrationException("Email " + email
                    + " is already being used. Please choose another.");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User userFromDB = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDB);
        return userFromDB;
    }
}
