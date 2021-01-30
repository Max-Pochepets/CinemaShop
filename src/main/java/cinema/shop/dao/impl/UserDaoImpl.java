package cinema.shop.dao.impl;

import cinema.shop.dao.UserDao;
import cinema.shop.lib.DaoImpl;
import cinema.shop.lib.exception.DataProcessException;
import cinema.shop.model.User;
import cinema.shop.util.HibernateUtil;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;

@DaoImpl
public class UserDaoImpl implements UserDao {
    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Could not save user " + user + ". ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            user = session.createQuery("from User where email=:email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            throw new DataProcessException("Could not find user by email " + email + ". ", e);
        }
    }
}
