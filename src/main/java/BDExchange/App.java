package BDExchange;

import BDExchange.Dao.UserDao;
import BDExchange.Domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class App {
    Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        new App().start();
    }

    private void start() {
        log.info("Starting App...");
        EntityManager em = Persistence.createEntityManagerFactory("MySQL").createEntityManager();
        UserDao userDao = new UserDao(em);
        userDao.insert(new User("Robert", "12345"));
    }
}
