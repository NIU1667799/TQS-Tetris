package tqs.tetris.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

/*
 * tests for the Model part of the Board class
 * TO TEST:
 * dimension, board created correctly
 * empty cells in the board
 * invalid size of board --> throw exceptions
 * ROW-->y, COL-->x
 *  y=3 | (0,3) (1,3) (2,3) (3,3) (4,3)
    y=2 | (0,2) (1,2) (2,2) (3,2) (4,2)
    y=1 | (0,1) (1,1) (2,1) (3,1) (4,1)
    y=0 | (0,0) (1,0) (2,0) (3,0) (4,0)   ← fila inferior
        x=0   x=1   x=2   x=3   x=4
 */

public interface Tetromino {
    void setPosition(int x, int y);
    int[][] getCells();
}

class MockTetromino implements Tetromino {
    private final int[][] cells;
    private int posX;
    private int posY;

    public MockTetromino(int[][] cells) {
        this.cells = cells;
        this.posX = 0;
        this.posY = 0;
    }

    @Override
    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    @Override
    public int[][] getCells() {
        int n = cells.length;
        int[][] abs = new int[n][2];
        for (int i = 0; i < n; i++) {
            abs[i][0] = cells[i][0] + posX; // x = dx + posX
            abs[i][1] = cells[i][1] + posY; // y = dy + posY
        }
        return abs;
    }
}

public class BoardTest {
    Board board;
    Tetromino tetromino;

    @BeforeEach
    void setUp() throws Exception {
        board = new Board(10,20);
        tetromino = new MockTetromino();
    }

    @Test
    void boardInicialization() {
        assertEquals(10, board.getWidth());
        assertEquals(20, board.getHeight());
    }
    
    @Test
    void boardEmptyCells() {
        for(int y = 0; y<board.getHeight(); y++) {
            for(int x = 0; x<board.getWidth(); x++) {
                assertTrue(board.isEmpty(x,y));
            }
        }
    }

    /** MOCKING tetromino object */
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
    void testCollides() {
        int W = board.getWidth();
        int H = board.getHeight();

        //colliding left wall, x<0
        Tetromino t = mockTetromino(-1,5,0,5,1,5,2,5);
        assertTrue(board.collides(t));
        //borde x = 0 no colisiona
        Tetromino t1 = mockTetromino(0,5,1,5,2,5,3,5);
        assertFalse(board.collides(t1));

        //colliding right wall, x >= W
        Tetromino t2 = mockTetromino(W,10);
        assertTrue(board.collides(t2));
        //x = W-1 no colisiona
        Tetromino t22 = mockTetromino(W-1,10);
        assertFalse(board.collides(t22));

        //colliding bottom y<0
        Tetromino t3 = mockTetromino(3,-1);
        assertTrue(board.collides(t3));
        //y=0 no colisiona
        Tetromino t33 = mockTetromino(3,0, 4,0, 5,0, 6,0);
        assertFalse(board.collides(t33));

        //colliding top y >= H
        Tetromino ttop = mockTetromino(4,H);
        assertTrue(board.collides(ttop));
        //y = H-1 no colisiona
        Tetromino ttop2 = mockTetromino(4,H-1);
        assertFalse(board.collides(ttop2));

        //colliding with fixed blocks
        board.setCell(5,0, true);
        Tetromino t4 = mockTetromino(5,0);
        assertTrue(board.collides(t4));
        //not colliding
        Tetromino t5 = mockTetromino(3,3,4,3,5,3,6,3);
        assertFalse(board.collides(t5));

        board.setCell(0, 0, true);
        Tetromino t6 = mockTetromino(-1,,0, 0,0);
        assertTrue(board.collides(t6));
    }

    /**
     * comprobar de todo el board, si hay alguna fila completa
     */
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

        //fil two rows
        for (int x = 0; x < board.getWidth(); x++) {
            board.setCell(x, 0, true);
            board.setCell(x, 1, true);
        }
        cleared = board.clearLines();
        assertEquals(2, cleared);
        for (int x = 0; x < board.getWidth(); x++) {
            assertTrue(board.isEmpty(x, 0));
            assertTrue(board.isEmpty(x, 1));
        }

        //fill two non consecutive rows
        for (int x = 0; x < board.getWidth(); x++) {
            board.setCell(x, 0, true);
            board.setCell(x, 2, true);
        }
        cleared = board.clearLines();
        assertEquals(2, cleared);
        for (int x = 0; x < board.getWidth(); x++) {
            assertTrue(board.isEmpty(x, 0));
            assertTrue(board.isEmpty(x, 1));
            assertTrue(board.isEmpty(x, 2));
        }

        for(int x=0; x <board.getWidth(); x++) {
            for(int y=0; y<board.getHeight(); y++) {
                //board.setCell(x, y, true);
            }
        }
    }
}
