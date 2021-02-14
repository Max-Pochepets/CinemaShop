package cinema.shop.security.authentication;

import cinema.shop.model.User;

public interface AuthenticationService {
    User register(User user);
}
