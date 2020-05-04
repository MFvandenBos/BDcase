package BDExchange.System;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StateTest {
    @Test @DisplayName("Scanner Input Test")
    public void whenGetInputIsCalledReturnTestInput() {
        // Given
        ScannerWrapper scannerMock = mock(ScannerWrapper.class);
        WelcomeState state = new WelcomeState(new StateManager());
        state.setScanner(scannerMock);

        // When
        when(scannerMock.getNextLine()).thenReturn("testInput");
        String actualResult = state.getInput();

        // Then
        assertEquals("testInput", actualResult);
    }
}