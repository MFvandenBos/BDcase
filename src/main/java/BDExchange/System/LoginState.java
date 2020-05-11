package BDExchange.System;

import BDExchange.Dao.UserDao;
import BDExchange.Domain.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

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

        log.warn("----------Login----------");
        while(loggingIn) {
            log.warn("Please enter your email address.");
            emailAddress = getInputString();
            log.warn("Please enter your password. Note that passwords are case-sensitive.");
            password = getInputString();

            try{
                User user = userDao.getUserByEmailAndPassword(emailAddress, password);
                if(user != null) {
                    loggingIn = false;
                    stateManager.setLoggedInUser(user);
                }
            }catch(Exception e) {
                log.error("You have entered an invalid email address or password. " +
                        "Please enter the correct details and try again. " +
                        "Don't forget that the password is case sensitive.");
            }
        }
         stateManager.changeAndUpdateState("LoggedOnState");
    }
}
