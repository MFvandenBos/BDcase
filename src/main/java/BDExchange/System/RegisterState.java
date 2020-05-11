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
        String livingAddress;
        List<DeliveryOptions> selectedOptions;

        log.warn("----------Register----------");
        while(selecting) {
            log.warn("\nPlease enter a valid email address for your user account:");
            emailAddress = getInputString();
            password = "password123";
            livingAddress = "";

            // TODO use batching to check if email already exists.
            log.warn("\nTo complete your registration you must select which delivery options you support.\n");
            selectedOptions = selectedDeliveryOptions();

            if(selectedOptions.contains(DeliveryOptions.AFHALENTHUIS)) {
                log.warn("A home address is required when the " + DeliveryOptions.AFHALENTHUIS + " option is selected. ");
                log.warn("Please input a home address.");
                livingAddress = getInputString();
            }

            if(userDao.insert(new User(emailAddress, password, livingAddress, selectedOptions))) {
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
        stateManager.changeAndUpdateState("Home");
    }

    public List<DeliveryOptions> selectedDeliveryOptions() {
        List<DeliveryOptions> selectedDeliveryOptions = new ArrayList<>();
        int selection;
        boolean selectingDeliveryOptions = true;
        boolean selectingAnotherOption = true;

        while(selectingDeliveryOptions) {
            selectingAnotherOption = true;
            log.warn("\nPlease select the number of a delivery option to add it your list.");
            showNumberedDeliveryOptions();

            try{
                selection = getInputInteger();
                if(selection >= 0 && selection < DeliveryOptions.values().length) {
                    if(selectedDeliveryOptions.contains(DeliveryOptions.values()[selection])) {
                        log.error("This delivery option has already been added to the list!");
                    } else {
                        log.warn(DeliveryOptions.values()[selection].name() + " added!\n");
                        selectedDeliveryOptions.add(DeliveryOptions.values()[selection]);

                        log.warn("Currently selected:");
                        for (DeliveryOptions deliveryOption : selectedDeliveryOptions) {
                            log.warn(deliveryOption.name());
                        }

                        // TODO loop this question if the input is incorrect.
                        while(selectingAnotherOption) {
                            log.warn("Would you like to select another delivery option?");
                            log.warn("[1] YES  |  [2] NO");
                            try {
                                selection = getInputInteger();
                                switch (selection) {
                                    case 1:
                                        selectingAnotherOption = false;
                                        break;
                                    case 2:
                                        selectingAnotherOption = false;
                                        selectingDeliveryOptions = false;
                                        break;
                                    default:
                                        log.error("Please input a valid option.");
                                        break;
                                }
                            } catch (NumberFormatException numberFormatException) {
                                log.error("Please input a number.");
                            }
                        }
                    }
                } else {
                    log.error("[" + selection + "] is not a valid option. Please input a valid option");
                }
            } catch(NumberFormatException numberFormatException) {
                log.error("Please input a number.");
            } catch(Exception e) {
                log.error("Please input the option correctly.");
            }
        }
        return selectedDeliveryOptions;
    }

    public void showNumberedDeliveryOptions() {
        DeliveryOptions[] deliveryOption = DeliveryOptions.values();
        for(int i = 0; i < deliveryOption.length; i++) {
            log.warn("[" + i + "] " + deliveryOption[i]);
        }
    }
}
