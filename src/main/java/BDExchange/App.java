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
        // Show welcome menu and save the chosen option
        int choice = welcomeMenu();

        // Show selected menu
        // menuManager


//        EntityManager em = Persistence.createEntityManagerFactory("MySQL").createEntityManager();
//        UserDao userDao = new UserDao(em);
//        userDao.insert(new User("Robert", "12345"));
    }

    private int welcomeMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        log.info("Welcome <Guest> to the BD Exchange!");
        log.info("You need to be logged in to be able to view the contents of this website!\n");

        while(true) {
            log.info("Please input the number to make your choice.");
            for (WelcomeOptions value : WelcomeOptions.values()) {
                log.info(value.ordinal() + " - " + value);
            }

            selection = input.nextInt();

            try {
                log.info(WelcomeOptions.values()[selection] + " has been selected.");
                return selection;
            } catch (Exception e) {
                log.error("Menu option not found!");
                log.error("Please enter a correct number.\n");
            }
        }
    }
}


