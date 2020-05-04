package BDExchange.System;

public class LoggedOnState extends State {
    public LoggedOnState(StateManager stateManager, String... options) {
        super(stateManager, options);
    }

    @Override
    public void updateState() {
        log.warn("Hello " + StateManager.loggedInUser.getEmailaddress() + ". You are now logged in.\n\n");

        log.warn("Please select an option to continue...");
        for (String option : optionList) {
            log.warn(option);
        }
    }
}
