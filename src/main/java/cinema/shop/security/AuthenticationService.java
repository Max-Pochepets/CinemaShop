package cinema.shop.security;

import cinema.shop.lib.exception.AuthenticationException;
import cinema.shop.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
