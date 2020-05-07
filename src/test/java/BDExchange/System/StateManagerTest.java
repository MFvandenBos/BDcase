package BDExchange.System;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class StateManagerTest {
    @Test @DisplayName("Change State Test")
    public void whenChangeStateIsCalledCurrentStateShouldReturnNewState() {
        // Given
        StateManager stateManager = new StateManager();
        HomeState oldState = new HomeState(stateManager, "Test");
        RegisterState newState = new RegisterState(stateManager, "Test");

        // When
        stateManager.changeState(oldState);
        State actualResult1 = stateManager.getCurrentState();
        stateManager.changeState(newState);
        State actualResult2 = stateManager.getCurrentState();

        // Then
        assertAll(
                () -> assertEquals(oldState, actualResult1),
                () -> assertEquals(newState, actualResult2)
        );
    }

}