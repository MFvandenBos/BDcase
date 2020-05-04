package BDExchange.System;

public class ExitState extends State{
    public ExitState(StateManager stateManager, String... options) {
        super(stateManager, options);
    }

    @Override
    public void updateState() {
        log.warn("\nClosing application...");
        System.exit(0);
    }
}
