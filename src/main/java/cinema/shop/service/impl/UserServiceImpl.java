package cinema.shop.service.impl;

import cinema.shop.dao.UserDao;
import cinema.shop.model.User;
import cinema.shop.service.UserService;
import cinema.shop.util.HashUtil;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        String encodedPassword = getEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userDao.add(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id).orElseThrow(()
                -> new RuntimeException("There is no such user with id " + id + "."));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    private PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
