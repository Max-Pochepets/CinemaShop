package cinema.shop.dao.impl;

import cinema.shop.dao.OrderDao;
import cinema.shop.lib.DaoImpl;
import cinema.shop.lib.exception.DataProcessException;
import cinema.shop.model.Order;
import cinema.shop.model.User;
import cinema.shop.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

@DaoImpl
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Could not save order "
                    + order + " item. ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Order o "
                    + "join fetch o.tickets "
                    + "where o.user = :user", Order.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessException("Could not get orders history for user "
                    + user.getEmail() + ". ", e);
        }
    }
}
