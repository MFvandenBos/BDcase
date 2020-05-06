package BDExchange.System;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.List;

public abstract class State {
    protected final List<String> optionList;
    protected final StateManager stateManager;
    ScannerWrapper scanner = new ScannerWrapper();
    Logger log = LoggerFactory.getLogger(State.class);

    public State(StateManager stateManager, String... options) {
        this.stateManager = stateManager;
        optionList = Arrays.asList(options);
    }

    public String getInputString() {
        return scanner.getNextLine();
    }

    public int getInputInteger() {
        return scanner.getNextInt();
    }

    public void showNumberedOptionList() {
        for(int i = 0; i < optionList.size(); i++) {
            log.warn("[" + i + "] " +  optionList.get(i));
        }
    }

    protected void changeState(String option) {
        stateManager.changeAndUpdateState(option);
    }

    public abstract void updateState();

    public void setScanner(ScannerWrapper input) { this.scanner = input; }
}
