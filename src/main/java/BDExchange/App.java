package BDExchange;

import BDExchange.Dao.UserDao;
import BDExchange.Domain.User;
import BDExchange.Domain.WelcomeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class App {
    Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        new App().start();
    }

    private void start() {
        int choice = welcomeMenu();
        log.info(WelcomeOptions.values()[choice] + " has been selected.");


//        EntityManager em = Persistence.createEntityManagerFactory("MySQL").createEntityManager();
//        UserDao userDao = new UserDao(em);
//        userDao.insert(new User("Robert", "12345"));
    }

    private int welcomeMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        log.info("Welcome <Guest> to BD Exchange!");
        log.info("You need to be logged in to be able to view the contents of this website!\n");

        log.info("Please input the number to make your choice.");
        log.info(WelcomeOptions.Login.ordinal() + " - " + WelcomeOptions.Login);
        log.info(WelcomeOptions.Register.ordinal() + " - " + WelcomeOptions.Register);
        log.info(WelcomeOptions.Quit.ordinal() + " - " + WelcomeOptions.Quit);

        selection = input.nextInt();
        return selection;
        // TODO index out of bounds exception handling
    }
}


