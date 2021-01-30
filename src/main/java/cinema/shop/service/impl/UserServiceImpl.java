package cinema.shop.service.impl;

import cinema.shop.dao.UserDao;
import cinema.shop.lib.Inject;
import cinema.shop.lib.ServiceImpl;
import cinema.shop.model.User;
import cinema.shop.service.UserService;
import java.util.Optional;

@ServiceImpl
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
