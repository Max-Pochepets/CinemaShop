package cinema.shop.dao.impl;

import cinema.shop.dao.MovieSessionDao;
import cinema.shop.lib.DaoImpl;
import cinema.shop.lib.exception.DataProcessException;
import cinema.shop.model.MovieSession;
import cinema.shop.util.HibernateUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@DaoImpl
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> getSessionsByDate
                    = session.createQuery("select ms from MovieSession ms "
                    + "left join fetch ms.movie "
                    + "left join fetch ms.cinemaHall "
                    + "where ms.movie.id =:movie_id "
                        + "and date_format(ms.showTime, '%Y-%m-%d')=:date", MovieSession.class);
            getSessionsByDate.setParameter("movie_id", movieId);
            getSessionsByDate.setParameter("date", DateTimeFormatter.ISO_LOCAL_DATE.format(date));
            return getSessionsByDate.getResultList();
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessException("Could not add movie session "
                    + movieSession + ". ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
