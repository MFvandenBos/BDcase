package BDExchange.System;

import BDExchange.Dao.UserDao;
import BDExchange.Domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class RegisterState extends State{
    Logger log = LoggerFactory.getLogger(RegisterState.class);
    public RegisterState(StateManager stateManager, String... options) {
        super(stateManager, options);
    }

    @Override
    public void updateState() {
        EntityManager em = Persistence.createEntityManagerFactory("MySQL").createEntityManager();
        UserDao userDao = new UserDao(em);
        boolean selecting = true;

        String emailAddress;
        String password;

        while(selecting) {
            log.warn("\nPlease enter a valid email address for your user account:");
            emailAddress = getInput();
            log.warn("Please enter a password for your user account. Note that passwords are case-sensitive.");
            password = getInput();

            // TODO Check if emailaddress already exists
            // TODO Encrypt password.
            if(userDao.insert(new User(emailAddress, password))) {
                log.warn("\nWelcome " + emailAddress + "!\n\n" +
                        "your DB Exchange account has been created!\n" +
                        "your password = '" + password + "' \n" +
                        "you are now able to log in and browse all available goods.\n\n" +
                        "-The DB Exchange team.\n\n");
                selecting = false;
            } else {
                log.error("Error while registering new account!\n");
            }
        }
        stateManager.changeState("Welcome");
    }
}
