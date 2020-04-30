package BDExchange.Dao;

import BDExchange.Domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class UserDao {
    Logger log = LoggerFactory.getLogger(UserDao.class);
    private final EntityManager em;
    public UserDao(EntityManager em) { this.em = em; }

        public boolean insert(User newUser) {
        try {
            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            log.error("An error has occurred while inserting a new user into the database: " + e.getMessage());
            return false;
        }
    }


}
