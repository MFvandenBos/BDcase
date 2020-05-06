package BDExchange.System;

import BDExchange.Dao.UserDao;
import BDExchange.Domain.DeliveryOptions;
import BDExchange.Domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

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
        DeliveryOptions[] selectedOptions;

        while(selecting) {
            log.warn("\nPlease enter a valid email address for your user account:");
            emailAddress = getInputString();
            password = "password123";

            // TODO use batching to check if email already exists before continueing.
            log.warn("\nTo complete your registration you must select which delivery options you support.\n");
            selectedOptions = selectedDeliveryOptions();

            if(userDao.insert(new User(emailAddress, password, selectedOptions))) {
                log.warn("\nWelcome " + emailAddress + "!\n\n" +
                        "your DB Exchange account has been created!\n" +
                        "your password = '" + password + "' \n" +
                        "you are now able to log in and browse all items.\n\n" +
                        "-The DB Exchange team.\n\n");
                selecting = false;
            } else {
                log.error("Error while registering new account!\n");
            }
        }
        stateManager.changeAndUpdateState("Welcome");
    }

    public DeliveryOptions[] selectedDeliveryOptions() {
        List<DeliveryOptions> opt = new ArrayList<>();

        int selection;
        boolean selecting = true;
        while(selecting) {
            log.warn("\nPlease select the number of a delivery option to add it your list.");
            showNumberedOptionList();

            try{
                selection = getInputInteger();
                if(optionList.contains(DeliveryOptions.values()[selection].name())) {
                    if(opt.contains(DeliveryOptions.values()[selection])) {
                        log.error("This delivery option has already been added to the list!");
                    } else {
                        log.warn(DeliveryOptions.values()[selection].name() + " added!\n");
                        opt.add(DeliveryOptions.values()[selection]);

                        log.warn("Currently selected:");
                        for (DeliveryOptions deliveryOption : opt) {
                            log.warn(deliveryOption.name());
                        }

                        log.warn("Would you like to select another delivery option?");
                        log.warn("[1] YES  |  [2] NO");
                        selection = scanner.getNextInt();
                        if(selection != 1) { selecting = false;}
                    }
                } else {
                    log.error("<" + selection + "> is not a valid option. Please input the option correctly (Case sensitive).");
                }
            } catch(Exception e) {
                log.error("Please input the option correctly.");
            }
        }
        DeliveryOptions[] options = new DeliveryOptions[opt.size()];
        options = opt.toArray(options);
        return options;
    }
}
