package BDExchange.System;

public class LoggedOnState extends State {
    public LoggedOnState(StateManager stateManager, String... options) {
        super(stateManager, options);
    }

    @Override
    public void updateState() {
        log.warn("Welcome " + StateManager.loggedInUser.getEmailaddress() + " to the BD Exchange!");

        log.warn("Please select an option to continue...");
        for (String option : optionList) {
            log.warn(option);
        }
    }
}
