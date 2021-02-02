package cinema.shop.security;

import static cinema.shop.util.HashUtil.hashPassword;

import cinema.shop.lib.Inject;
import cinema.shop.lib.ServiceImpl;
import cinema.shop.lib.exception.AuthenticationException;
import cinema.shop.model.User;
import cinema.shop.service.UserService;
import java.util.Optional;

@ServiceImpl
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

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
        userService.add(user);
        return user;
    }
}
