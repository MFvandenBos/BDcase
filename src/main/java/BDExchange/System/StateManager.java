package BDExchange.System;

import BDExchange.Domain.User;

import java.util.HashMap;
import java.util.Map;

public class StateManager {

    private State currentState;
    final static Map<String, State> statesByOptionsMap = new HashMap<>();
    private User loggedInUser;

    public void initialize() {
        // initialize all states
        HomeState homeState = new HomeState( this,"Login", "Register", "Exit");
        RegisterState registerState = new RegisterState(this);
        ExitState exitState = new ExitState(this);
        LoginState loginState = new LoginState(this);
        LoggedOnState loggedonState = new LoggedOnState(this, "Products", "Wishlist");

        // Map options to states
        addToMap("Home", homeState);
        addToMap("Register", registerState);
        addToMap("Exit", exitState);
        addToMap("Login", loginState);
        addToMap("LoggedOnState", loggedonState);

        // Set first state to welcome state
        changeState(homeState);
        updateState();
    }

    public void addToMap(String option, State state) {
        statesByOptionsMap.put(option, state);
    }
    public void changeState(State state) {
        currentState = state;
    }
    public void changeAndUpdateState(String option) {
        changeState(statesByOptionsMap.getOrDefault(option, currentState));
        updateState();
    }

    public void updateState() {
        currentState.updateState();
    }

    public State getCurrentState() {
        return currentState;
    }
    public User getLoggedInUser() { return loggedInUser; }
    protected void setLoggedInUser(User loggedInUser) { this.loggedInUser = loggedInUser; }
}
