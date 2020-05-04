package BDExchange.System;

import BDExchange.Dao.UserDao;
import BDExchange.Domain.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.sql.Statement;

public class LoginState extends State {
    public LoginState(StateManager stateManager, String... options) {
        super(stateManager, options);
    }

    @Override
    public void updateState() {
        EntityManager em = Persistence.createEntityManagerFactory("MySQL").createEntityManager();
        UserDao userDao = new UserDao(em);
        boolean loggingIn = true;

        String emailAddress;
        String password;

        while(loggingIn) {
            log.warn("\nPlease enter your email address.");
            emailAddress = getInput();
            log.warn("Please enter your password. Note that passwords are case-sensitive.");
            password = getInput();

            try{
                User user = userDao.getUserByEmailAndPassword(emailAddress, password);
                if(user != null) {
                    loggingIn = false;
                    StateManager.loggedInUser = user;
                    log.warn("\n\n\n\n\n\n\n\n\n\n\n\n");
                }
            }catch(Exception e) {
                log.error("You have entered an invalid email address or password. " +
                        "Please enter the correct details and try again. " +
                        "Don't forget that the password is case sensitive.");
            }
        }
         stateManager.changeState("LoggedOnState");
    }
}
