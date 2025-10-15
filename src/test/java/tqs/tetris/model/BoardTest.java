package tqs.tetris.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

/*
 * tests for the Model part of the Board class
 * TO TEST:
 * dimension, board created correctly
 * empty cells in the board
 * invalid size of board --> throw exceptions
 */

public class BoardTest {
    Board board;

    @BeforeEach
    void setUp() throws Exception {
        board = new Board(10,20);
    }

    @Test
    void boardInicialization() {
        assertEquals(10, board.getWidth());
        assertEquals(20, board.getHeight());
    }

    @Test
    void boardInicializationInvalideSize() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->{
            new Board(0,20);
        });

        exception = assertThrows(IllegalArgumentException.class, () ->{
            new Board(10,-5);
        });
    }
    
    @Test
    void boardEmptyCells() {
        for(int y = 0; y<board.getHeight(); y++) {
            for(int x = 0; x<board.getWidth(); x++) {
                assertTrue(board.isEmpty(x,y));
            }
        }
    }

    @Test
    void boardPlaceTetromino() {
        Tetromino tetromino = new Tetromino(TetrominoType.I);
        tetromino.setPosition(4, 0); // Place at the top center
        board.placeTetromino(tetromino);
        //should be ocupied
        for(int x = 4; x < 8; x++) {
            assertFalse(board.isEmpty(x, 0));
        }
    }

    @Test
    void boardCollisions() {
        Tetromino t1 = Tetromino.ofType(TetrominoType.I);
        t1.setPosition(0, 0);
        assertTrue(board.collides(t1.moveDown(-1,0)));
    }

    @Test
    void setAndGetCell() {
        // initially empty
        assertTrue(board.isEmpty(2,2));

        // occupy then free
        board.setCell(2,2, true);
        assertFalse(board.isEmpty(2,2));
        board.setCell(2,2, false);
        assertTrue(board.isEmpty(2,2));
    }

    @Test
    void clearLines() {
        // fill the bottom row (y=0)
        for (int x = 0; x < board.getWidth(); x++) {
            board.setCell(x, 0, true);
        }
        int cleared = board.clearLines();
        assertEquals(1, cleared);
        // should be empty
        for (int x = 0; x < board.getWidth(); x++) {
            assertTrue(board.isEmpty(x, 0));
        }
    }
}
