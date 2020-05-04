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

    public abstract void updateState();

    public List<String> getOptionList() { return optionList; }
    public void setScanner(ScannerWrapper input) { this.scanner = input; }
}
