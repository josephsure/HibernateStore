package dao.hibernateDAO;

import dao.HibernateUtils;
import dao.interfaces.DAOReviews;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.Purchase;
import model.Review;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateQuerys;

import javax.management.Query;
import javax.persistence.NamedQuery;
import java.util.List;

@Log4j2
public class HibernateDAOReviews implements DAOReviews {

    @Override
    public Review get(int id) {
        return null;
    }

    @Override
    public List<Review> getReviewByItem(int id) {
        Session session = null;
        List<Review> list = null;
        try {
            session = HibernateUtils.getSession();
            list = session.createNamedQuery("getReviewsByItem",Review.class)
                    .setParameter("id",id)
                    .getResultList();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if (session != null){
                session.close();
            }

        }
        return list;
    }

    @Override
    public List<Review> getReviewByItemByUser(int idUser, int idItem) {
        return null;
    }

    @Override
    public List<Review> getAll() {
        Session session = null;
        List<Review> list = null;
        try{
            session = HibernateUtils.getSession();
            list = session.createQuery(HibernateQuerys.FROM_REVIEW_,Review.class).list();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if (session != null){
                session.close();
            }

        }

        return list;
    }

    @Override
    public List<Review> getAllByUser(int id) {
        Session session = null;
        List<Review> list = null;
        try {
            session = HibernateUtils.getSession();

            list = session.createNamedQuery("reviewsByUser" ,Review.class)
                    .setParameter("id",id)
                    .getResultList();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if (session != null){
                session.close();
            }

        }
        return list;
    }

    @Override
    public boolean save(Review review) {
        Session session = null;
        boolean confirmacion = false;
        try {
            session = HibernateUtils.getSession();
            session.save(review);
            confirmacion = true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if (session != null){
                session.close();
            }

        }
        return confirmacion;
    }

    @Override
    public int update(Review review) {
        Session session = null;
        Transaction transaction = null;
        int confirmacion = 0;

        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.update(review);
            transaction.commit();
            confirmacion = 1;

        }catch (Exception e){
            log.error(e.getMessage(),e);
            if (transaction != null){
                transaction.rollback();
            }
        }finally {
            if (session != null){
                session.close();
            }
        }
        return confirmacion;
    }

    @Override
    public int delete(Review review) {
        Session session = null;
        Transaction transaction = null;
        int confirmacion = 0;

        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.delete(review);
            transaction.commit();
            confirmacion = 1;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            if (transaction != null){
                transaction.rollback();
            }
        }finally {
            if (session != null){
                session.close();
            }
        }
        return confirmacion;
    }

    @Override
    public List<Review> getReviewByCustomer(int id) {
        Session session = null;
        List<Review> list = null;
        try{
            session = HibernateUtils.getSession();
            list = session.createNamedQuery("getReviewsByCustomer",Review.class)
                    .setParameter("id",id)
                    .getResultList();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if (session != null){
                session.close();
            }
        }
        return list;
    }

    @Override
    public int checkItemReview(int id) {

        Session session = null;
        int confirmacion = -1;
        try{
            session = HibernateUtils.getSession();
            confirmacion = (session.createNamedQuery("checkItemReview",Long.class).setParameter("id",id).uniqueResult()).intValue();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if (session != null){
                session.close();
            }
        }
        return confirmacion;
    }
}
