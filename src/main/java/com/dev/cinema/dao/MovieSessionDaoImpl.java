package com.dev.cinema.dao;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.dev.cinema.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger logger = Logger.getLogger(MovieSessionDaoImpl.class);
    private final SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> movieSessionQuery =
                    session.createQuery("from MovieSession where movie.id = :movieId "
                    + " and showTime between :begin and :end ", MovieSession.class);
            movieSessionQuery.setParameter("movieId", movieId);
            movieSessionQuery.setParameter("begin", date.atStartOfDay());
            movieSessionQuery.setParameter("end", date.atTime(LocalTime.MAX));
            return movieSessionQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get movie sessions by movie id "
                    + movieId + " at date: " + date.toString() + " from DB", e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            logger.info("Insert moveSession " + movieSession + " in to data");
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert moveSession "
                    + movieSession + " in to data", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public MovieSession findById(Long sessionId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MovieSession.class, sessionId);
        }
    }
}
