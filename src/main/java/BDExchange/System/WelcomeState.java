package BDExchange.System;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class WelcomeState extends State {
    Logger log = LoggerFactory.getLogger(WelcomeState.class);

    public WelcomeState(StateManager stateManager, String... options) {
        super(stateManager, options);
    }

    @Override
    public void updateState() {
        String selection;
        boolean selecting = true;
        Scanner input = new Scanner(System.in);

        log.warn("Welcome <Guest> to the BD Exchange!");
        log.warn("You need to log in to be able to browse all available items!\n");

        while(selecting) {
            log.warn("\nPlease select an option to continue...");
            for (String option : optionList) {
                log.warn(option);
            }

            try {
                selection = input.nextLine();
                if(optionList.contains(selection)) {
                    log.warn("<" + selection + "> has been selected!");
                    changeState(selection);
                    selecting = false;
                } else {
                    log.error("<" + selection + "> is not a valid option. Please input the option correctly (Case sensitive).");
                }
            } catch (Exception e) {
                log.error("Please input the option correctly (Case sensitive).");
            }
        }
    }

    private void changeState(String option) {
        stateManager.changeState(option);
    }
}
