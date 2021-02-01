package cinema.shop.service.impl;

import cinema.shop.dao.UserDao;
import cinema.shop.lib.Inject;
import cinema.shop.lib.ServiceImpl;
import cinema.shop.model.User;
import cinema.shop.service.UserService;
import cinema.shop.util.HashUtil;
import java.util.Optional;

@ServiceImpl
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        String password = user.getPassword();
        byte[] salt = HashUtil.getSalt();
        String hashedPassword = HashUtil.hashPassword(password, salt);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
