package BDExchange.System;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WelcomeState extends State {
    Logger log = LoggerFactory.getLogger(WelcomeState.class);
    public WelcomeState(StateManager stateManager, String... options) { super(stateManager, options); }

    @Override
    public void updateState() {
        String selection;
        boolean selecting = true;

        log.warn("Welcome to the BD Exchange!");
        log.warn("You need to log in to be able to browse all available items!\n");

        while(selecting) {
            log.warn("Please select an option to continue...");
            for (String option : optionList) {
                log.warn(option);
            }

            // TODO Duplicate code in else{} and catch(Exception e). Needs refactoring.
            try {
                selection = getInput();
                if(optionList.contains(selection)) {
                    selecting = false;
                    changeState(selection);
                } else {
                    log.error("<" + selection + "> is not a valid option. Please input the option correctly (Case sensitive).");
                }
            } catch (Exception e) {
                log.error("Please input the option correctly (Case sensitive).");
            }
        }
    }
}
