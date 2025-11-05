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
 * ROW-->y, COL-->x
 *  y=3 | (0,3) (1,3) (2,3) (3,3) (4,3)
    y=2 | (0,2) (1,2) (2,2) (3,2) (4,2)
    y=1 | (0,1) (1,1) (2,1) (3,1) (4,1)
    y=0 | (0,0) (1,0) (2,0) (3,0) (4,0)   ← fila inferior
        x=0   x=1   x=2   x=3   x=4
 */

//helper for determine tetromino type
enum TetrominoType {I, O, T, S, Z, J, L}

class MockTetromino implements Tetromino {
    private final int[][] cells;
    private int posX;
    private int posY;

    public MockTetromino() {
        // Default shape: I horitzontal (0,0), (1,0), (2,0), (3,0)
        this(new int[][]{{0,0},{1,0},{2,0},{3,0}});
    }

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

    //tetromino fabric
    public static MockTetromino of(TetrominoType type, int rotationDeg) {
        int r = ((rotationDeg % 360) + 360) % 360; // normaliza
        int[][] rel;
        switch (type) {
            case I:
                rel = (r == 0 || r == 180)
                    ? new int[][]{{0,0},{1,0},{2,0},{3,0}}
                    : new int[][]{{0,0},{0,1},{0,2},{0,3}};
                break;
            case O:
                rel = new int[][]{{0,0},{1,0},{0,1},{1,1}};
                break;
            case T:
                if (r == 0)      rel = new int[][]{{0,0},{1,0},{2,0},{1,1}};
                else if (r==90)  rel = new int[][]{{1,0},{0,1},{1,1},{1,2}};
                else if (r==180) rel = new int[][]{{1,0},{0,1},{1,1},{2,1}};
                else             rel = new int[][]{{0,0},{0,1},{1,1},{0,2}};
                break;
            case S:
                if (r == 0 || r == 180) rel = new int[][]{{1,0},{2,0},{0,1},{1,1}};
                else                    rel = new int[][]{{0,0},{0,1},{1,1},{1,2}};
                break;
            case Z:
                if (r == 0 || r == 180) rel = new int[][]{{0,0},{1,0},{1,1},{2,1}};
                else                    rel = new int[][]{{1,0},{0,1},{1,1},{0,2}};
                break;
            case J:
                if (r == 0)      rel = new int[][]{{0,0},{0,1},{1,1},{2,1}};
                else if (r==90)  rel = new int[][]{{0,0},{1,0},{0,1},{0,2}};
                else if (r==180) rel = new int[][]{{0,0},{1,0},{2,0},{2,1}};
                else             rel = new int[][]{{1,0},{1,1},{1,2},{0,2}};
                break;
            case L:
                if (r == 0)      rel = new int[][]{{2,0},{0,1},{1,1},{2,1}};
                else if (r==90)  rel = new int[][]{{0,0},{0,1},{0,2},{1,2}};
                else if (r==180) rel = new int[][]{{0,0},{1,0},{2,0},{0,1}};
                else             rel = new int[][]{{0,0},{1,0},{1,1},{1,2}};
                break;
            default:
                throw new IllegalArgumentException("Tipo no soportado");
        }
    return new MockTetromino(rel);
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
        Tetromino tetromino = new MockTetromino();
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
        Tetromino t = MockTetromino.of(TetrominoType.I,0);
        t.setPosition(-1, 5);
        assertTrue(board.collides(t));
        //borde x = 0 no colisiona
        Tetromino t1 = MockTetromino.of(TetrominoType.I, 0);
        t1.setPosition(0, 5);
        assertFalse(board.collides(t1));

        //colliding right wall, x >= W
        Tetromino t2 = MockTetromino.of(TetrominoType.I, 0);
        t2.setPosition(W-3, 10);
        assertTrue(board.collides(t2));
        //x = W-1 no colisiona
        Tetromino t22 = MockTetromino.of(TetrominoType.I, 90);
        t22.setPosition(W-1, 3);
        assertFalse(board.collides(t22));

        //colliding bottom y<0
        Tetromino t3 = MockTetromino.of(TetrominoType.I, 90);
        t3.setPosition(3, -1);
        assertTrue(board.collides(t3));
        //y=0 no colisiona
        Tetromino t33 = MockTetromino.of(TetrominoType.I, 0);
        t33.setPosition(3, 0);
        assertFalse(board.collides(t33));

        //colliding top y >= H
        Tetromino ttop = MockTetromino.of(TetrominoType.I, 90); 
        ttop.setPosition(4, H-0);
        assertTrue(board.collides(ttop));
        //y = H-1 no colisiona
        Tetromino ttop2 = MockTetromino.of(TetrominoType.I, 0);
        ttop2.setPosition(4, H-1);
        assertFalse(board.collides(ttop2));

        //colliding with fixed blocks
        board.setCell(5,0, true);
        Tetromino t4 = MockTetromino.of(TetrominoType.O, 0);
        t4.setPosition(5,0);
        assertTrue(board.collides(t4));
        //not colliding
        Tetromino t5 = MockTetromino.of(TetrominoType.O, 0);
        t5.setPosition(6,1);
        assertFalse(board.collides(t5));
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
