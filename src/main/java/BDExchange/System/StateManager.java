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

        // Map state options to states
        statesByOptionsMap.put("Welcome", welcomeState);
        statesByOptionsMap.put("Register", registerState);

        // Set first state to welcome state
        changeState(welcomeState);
    }

    public void changeState(State state) {
        currentState = state;
        currentState.updateState();
    }

    public void changeState(String option) {
        changeState(statesByOptionsMap.getOrDefault(option, currentState));
    }

    public State getCurrentState() {
        return currentState;
    }
}
