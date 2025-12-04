package tqs.tetris.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * verify that the diferents methods (isPlayable, isFrozen, isGameOver)
 * behave correctly for each possible state.
 */
public class gameStateTest {
    @Test
    public void gameStateIsPlayableTest() {
        assertTrue(GameState.RUNNING.isPlayable());
        assertFalse(GameState.PAUSED.isPlayable());
        assertFalse(GameState.GAME_OVER.isPlayable());
    }

    @Test
    public void gameStateIsFrozenTest() {
        assertFalse(GameState.RUNNING.isFrozen());
        assertTrue(GameState.PAUSED.isFrozen());
        assertFalse(GameState.GAME_OVER.isFrozen());
    }

    @Test
    public void gameStateIsGameOverTest() {
        assertFalse(GameState.RUNNING.isGameOver());
        assertFalse(GameState.PAUSED.isGameOver());
        assertTrue(GameState.GAME_OVER.isGameOver());
    }
    
}
