package BDExchange;

import BDExchange.System.StateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App {
    Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        StateManager stateManager = new StateManager();
        stateManager.initialize();

    }
}


