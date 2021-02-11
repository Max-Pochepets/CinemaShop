package cinema.shop.security;

import cinema.shop.lib.exception.AuthenticationException;
import cinema.shop.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
