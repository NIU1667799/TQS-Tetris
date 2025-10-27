package tqs.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    public void gameConstructionTest() {
        Game game = new Game();
        MockBoard mockBoard = new MockBoard( 10, 20);
        game.setBoard(mockBoard);
        assertEquals(mockBoard, game.getBoard());

        MockTetraminoFactory mockFactory = new MockTetraminoFactory('I', 1);
        game.setTetraminoFactory(mockFactory);
        assertEquals(mockFactory, game.getTetraminoFactory());

        // Can be RUNNING, PAUSED, or GAME_OVER
        MockGameState mockState = new MockGameState("RUNNING");
        game.setGameState(mockState.getValue());
        assertEquals(mockState.getValue(), game.getGameState());

        Game game2 = new Game(mockBoard, mockFactory, mockState.getValue());
        assertEquals(mockBoard, game2.getBoard());
        assertEquals(mockFactory, game2.getTetraminoFactory());
        assertEquals(mockState.getValue(), game2.getGameState());
    }

    @Test
    public void gameStateTest() {
        MockBoard mockBoard = new MockBoard( 10, 20);
        MockTetraminoFactory mockFactory = new MockTetraminoFactory('I', 1);
        MockGameState mockStateRunning = new MockGameState("RUNNING");
        Game game = new Game(mockBoard, mockFactory, mockStateRunning.getValue());
        assertEquals(mockStateRunning.getValue(), game.getGameState());

        MockGameState mockStatePaused = new MockGameState("PAUSED");
        game.pause();
        assertEquals(mockStatePaused.getValue(), game.getGameState());

        game.resume();
        assertEquals(mockStateRunning.getValue(), game.getGameState());

        MockGameState mockStateGameOver = new MockGameState("GAME_OVER");
        game.gameOver();
        assertEquals(mockStateGameOver.getValue(), game.getGameState());
    }
}
