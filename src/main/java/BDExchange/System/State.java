package BDExchange.System;

import java.util.Arrays;
import java.util.List;

public abstract class State {
    protected final List<String> optionList;
    protected final StateManager stateManager;

    public State(StateManager stateManager, String... options) {
        this.stateManager = stateManager;
        optionList = Arrays.asList(options);
    }

    public List<String> getOptionList() { return optionList; }
    public abstract void updateState();

}
