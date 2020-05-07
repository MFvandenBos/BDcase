package BDExchange.System;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeState extends State {
    Logger log = LoggerFactory.getLogger(HomeState.class);
    public HomeState(StateManager stateManager, String... options) { super(stateManager, options); }

    @Override
    public void updateState() {
        String selection = "";
        boolean selecting = true;

        log.warn("Welcome to the BD Exchange!");
        log.warn("You need to log in to be able to browse all available items!\n");

        while(selecting) {
            log.warn("Please select the number of an option to continue...");
            showNumberedOptionList();

            // TODO Duplicate code in else{} and catch(Exception e). Needs refactoring?
            try {
                selection = optionList.get(getInputInteger());
                if(optionList.contains(selection)) {
                    selecting = false;
                    changeState(selection);
                }
            } catch(NumberFormatException numberFormatException) {
                log.error("Please input a number.");
            } catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                log.error("Not a valid option. Please select an option from the list.");
            } catch (Exception e) {
                log.error("An error has occurred while selecting an option.");
            }
        }
    }
}
