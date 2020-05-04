package BDExchange.System;

import java.util.HashMap;
import java.util.Map;

public class StateManager {

    private State currentState;
    final static Map<String, State> statesByOptionsMap = new HashMap<>();

    public void initialize() {
        // initialize all states
        WelcomeState welcomeState = new WelcomeState( this,"Login", "Register", "Quit");
        RegisterState registerState = new RegisterState(this);

        // Map options to states
        addToMap("Welcome", welcomeState);
        addToMap("Register", registerState);

        // Set first state to welcome state
        changeState(welcomeState);
        updateState();
    }

    public void addToMap(String option, State state) {
        statesByOptionsMap.put(option, state);
    }

    public void changeState(State state) {
        currentState = state;
    }

    public void changeState(String option) {
        changeState(statesByOptionsMap.getOrDefault(option, currentState));
        updateState();
    }

    public void updateState() {
        currentState.updateState();
    }


    public State getCurrentState() {
        return currentState;
    }
}
