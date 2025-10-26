package tqs.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    public void gameConstructionTest() {
        Game game = new Game();
        MockBoard mockBoard = new MockBoard();
        game.setBoard(mockBoard);
        assertEquals(mockBoard, game.getBoard());

        MockTetraminoFactory mockFactory = new MockTetraminoFactory();
        game.setTetraminoFactory(mockFactory);
        assertEquals(mockFactory, game.getTetraminoFactory());

        MockGameState mockState = new MockGameState();
        game.setGameState(mockState);
        assertEquals(mockState, game.getGameState());

        Game game2 = new Game(mockBoard, mockFactory, mockState);
        assertEquals(mockBoard, game2.getBoard());
        assertEquals(mockFactory, game2.getTetraminoFactory());
        assertEquals(mockState, game2.getGameState());
    }
}
