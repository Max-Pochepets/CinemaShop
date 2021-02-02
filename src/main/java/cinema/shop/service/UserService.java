package cinema.shop.service;

import cinema.shop.lib.ServiceImpl;
import cinema.shop.model.User;
import java.util.Optional;

@ServiceImpl
public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);
}
