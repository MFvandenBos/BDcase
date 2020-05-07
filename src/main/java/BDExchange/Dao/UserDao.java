package BDExchange.Dao;

import BDExchange.Domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UserDao {
    Logger log = LoggerFactory.getLogger(UserDao.class);

    @Inject
    private EntityManager em;
    public UserDao() {}
    public UserDao(EntityManager em) { this.em = em; }

    public boolean insert(User newUser) {
        try {
            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();
            return true;
        } catch (javax.persistence.RollbackException r) {
            log.error("The email address you have entered is invalid! Please try a different email address.");
            return false;
        }
        catch (Exception e) {
            log.error("An error has occurred while inserting a new user into the database: " + e.getMessage());
            return false;
        }
    }

    public User getUserByEmailAndPassword(String emailaddress, String password) {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.emailaddress = :firstarg AND u.password = :secondarg", User.class
        );
        query.setParameter("firstarg", emailaddress);
        query.setParameter("secondarg", password);
        return query.getSingleResult();
    }
}
