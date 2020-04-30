package BDExchange;

import BDExchange.Domain.MenuManager;
import BDExchange.Domain.MenuOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static BDExchange.Domain.MenuOptions.*;

public class App {
    Logger log = LoggerFactory.getLogger(App.class);
    public static MenuOptions currentMenu = HOME;

    public static void main(String[] args) {
        MenuManager menuManager = new MenuManager();

        while(true) {
            currentMenu = menuManager.showMenu(currentMenu);
        }
    }
}


