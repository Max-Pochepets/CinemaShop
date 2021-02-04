package cinema.shop.dao.impl;

import cinema.shop.dao.TicketDao;
import cinema.shop.lib.DaoImpl;
import cinema.shop.lib.exception.DataProcessException;
import cinema.shop.model.Ticket;
import cinema.shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@DaoImpl
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Could not add ticket " + ticket + " item. ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
