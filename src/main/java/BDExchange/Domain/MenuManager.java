package BDExchange.Domain;

import BDExchange.App;
import BDExchange.Dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

import static BDExchange.Domain.MenuOptions.*;

public class MenuManager {
    Logger log = LoggerFactory.getLogger(App.class);

    public MenuOptions showMenu(MenuOptions menuOption) {
        switch (menuOption) {
            case QUIT:
                log.warn("Exiting...");
                System.exit(0);
            case HOME:
                return homeMenu();
            case REGISTER:
                return registerMenu();
            case LOGIN:
                log.warn("Opening LOGIN...");
                break;
            default:
                log.error("MENU NOT FOUND!");
                break;
        }
        return HOME;
    }

    private MenuOptions homeMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        log.warn("Welcome <Guest> to the BD Exchange!");
        log.warn("You need to log in to be able to browse all available goods!\n");

        while(true) {
            log.warn("Please input the number to make your choice.");
            for (MenuOptions value : MenuOptions.values()) {
                if(value != HOME) {
                    log.warn(value.ordinal() + " - " + value);
                }
            }

            selection = input.nextInt();

            try {
                log.warn(MenuOptions.values()[selection] + " has been selected.");
                return MenuOptions.values()[selection]; // TODO check if user is allowed to select this if not logged on
            } catch (Exception e) {
                log.error("Menu option not found!");
                log.error("Please enter a correct number.\n");
            }
        }
    }

    private MenuOptions registerMenu() {
        EntityManager em = Persistence.createEntityManagerFactory("MySQL").createEntityManager();
        UserDao userDao = new UserDao(em);
        Scanner input = new Scanner(System.in);

        String emailAddress;
        String password;

        log.warn("\n\nPlease enter a valid email address for your user account:");
        emailAddress = input.nextLine();
        log.warn("Please enter a password for your user account. Note that passwords are case-sensitive.");
        password = input.nextLine();

        if(userDao.insert(new User(emailAddress, password))) {
            log.warn("\nWelcome " + emailAddress + "!\n\n" +
                    "your DB Exchange account has been created!\n" +
                    "your password = '" + password + "' \n" +
                    "you are now able to log in and browse all available goods.\n\n" +
                    "-The DB Exchange team.");
        } else {
            log.error("Error while registering new account!\n");
        }

        return HOME;
    }
}
