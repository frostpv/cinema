package com.dev.cinema.dao;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);

    @Override
    public Order add(Order order) {
        logger.info("Trying insert order entity into the database");
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
            throw new DataProcessingException("Can't insert order entity"
                    + order + " into the database", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        logger.info("Trying get order history");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT DISTINCT o FROM Order o "
                    + "LEFT JOIN FETCH o.tickets "
                    + "WHERE o.user = :user", Order.class)
                    .setParameter("user", user)
                    .getResultList();
        }
    }
}
