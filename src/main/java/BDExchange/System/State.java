package BDExchange.System;

import java.util.Arrays;
import java.util.List;

public abstract class State {
    protected final List<String> optionList;
    protected final StateManager stateManager;
    ScannerWrapper scanner = new ScannerWrapper();

    public State(StateManager stateManager, String... options) {
        this.stateManager = stateManager;
        optionList = Arrays.asList(options);
    }

    public String getInput() {
        return scanner.getNextLine();
    }

    protected void changeState(String option) {
        stateManager.changeState(option);
    }

    public abstract void updateState();

    public void setScanner(ScannerWrapper input) { this.scanner = input; }
}
