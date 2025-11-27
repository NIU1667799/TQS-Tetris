package tqs.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    public void gameConstructionTest() {
        Game game = new Game();
        MockBoard mockBoard = new MockBoard(10, 20);
        game.setBoard(mockBoard);
        assertEquals(mockBoard, game.getBoard());

        MockTetrominoFactory mockFactory = new MockTetrominoFactory('I', 1);
        game.setTetrominoFactory(mockFactory);
        assertEquals(mockFactory, game.getTetrominoFactory());

        // Can be RUNNING, PAUSED, or GAME_OVER
        game.setGameState(GameState.RUNNING);
        assertEquals(GameState.RUNNING, game.getGameState());

        Game game2 = new Game(mockBoard, mockFactory, GameState.RUNNING);
        assertEquals(mockBoard, game2.getBoard());
        assertEquals(mockFactory, game2.getTetrominoFactory());
        assertEquals(GameState.RUNNING, game2.getGameState());
    }

    @Test
    public void gameStateTest() {
        MockBoard mockBoard = new MockBoard(10, 20);
        MockTetrominoFactory mockFactory = new MockTetrominoFactory('I', 1); // ✅ fixed spelling
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
