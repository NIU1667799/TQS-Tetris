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

    /**
     * In this test, we will place different tetrominoes on the same board and test diferent collision cases
     * declare a tetromino mockobject and starts falling down until reaching to the bottom or collides with another tetromino
     */

    @Test
    void testColides(){
        int W = board.getWidth();
        int H = board.getHeight();

        //O shape in the center of the board
        Tetromino oCenter = MockTetromino.of(TetrominoType.O, 0);
        int ox = W/2;
        int oy = H/2;
        oCenter.setPosition(ox, oy);
        while(!board.collides(oCenter)) {
            oCenter.setPosition(ox, oy--); //falling down
        }

        oy = oy +1; //last non colliding
        oCenter.setPosition(ox, oy);
        board.placeTetromino(oCenter);

        //verify that O shape tetromino cells are occupied
        assertFalse(board.isEmpty(ox,0));
        assertFalse(board.isEmpty(ox+1,0));
        assertFalse(board.isEmpty(ox,1));
        assertFalse(board.isEmpty(ox+1,1));

        //I shape vertical colliding with O shape, should stop on top of O shape
        Tetromino iVertical = MockTetromino.of(TetrominoType.I, 90);
        int ix = ox; //same column as O shape
        int iy = H-1;
        iVertical.setPosition(ix, iy);
        while(!board.collides(iVertical)) {
            iVertical.setPosition(ix, iy--);
        }
        iy = iy +1; //last non colliding
        iVertical.setPosition(ix, iy);
        board.placeTetromino(iVertical);
        assertFalse(board.isEmpty(ix, 2));
        assertFalse(board.isEmpty(ix, 3));
        assertFalse(board.isEmpty(ix, 4));
        assertFalse(board.isEmpty(ix, 5));

        Tetromino o2 = MockTetromino.of(TetrominoType.O, 0);
        int ox2 = ox;
        int oy2 = H-2;
        o2.setPosition(ox2, oy2); //same column as I shape
        assertTrue(board.collides(o2));

        while (!board.collides(o2)) {
            oy2 = oy2 - 1;
            o2.setPosition(ox2, oy2);
        }
        oy2 = oy2 + 1;
        o2.setPosition(ox2, oy2);
        board.placeTetromino(o2);

        assertFalse(board.isEmpty(ox2,   6));
        assertFalse(board.isEmpty(ox2+1, 6));
        assertFalse(board.isEmpty(ox2,   7));
        assertFalse(board.isEmpty(ox2+1, 7));

        Tetromino z1 = MockTetromino.of(TetrominoType.Z, 0);
        z1.setPosition(ix-1, 2);
        assertTrue(board.collides(z1));

        Tetromino z2 = MockTetromino.of(TetrominoType.Z, 0);
        int zx = Math.max(0,ix-3);
        int zy = H-2;
        z2.setPosition(zx, zy);
        while(!board.collides(z2)) {
            zy = zy - 1;
            z2.setPosition(zx, zy);
        }
        zy = zy + 1;
        z2.setPosition(zx, zy);
        board.placeTetromino(z2);
        // Debe haber ocupado suelo/altura baja en el lado izquierdo
        assertFalse(board.isEmpty(zx,   0));
        assertFalse(board.isEmpty(zx+1, 0));
        assertFalse(board.isEmpty(zx+1, 1));
        assertFalse(board.isEmpty(zx+2, 1));

        Tetromino s1 = MockTetromino.of(TetrominoType.S, 0);
        int sx = Math.min(W-3, ix+3);
        int sy = H-2;
        s1.setPosition(sx, sy);
        while(!board.collides(s1)) {
            sy = sy - 1;
            s1.setPosition(sx, sy);
        }
        sy = sy + 1;
        s1.setPosition(sx, sy);
        board.placeTetromino(s1);
        // Debe haber ocupado suelo/altura baja en el lado derecho
        assertFalse(board.isEmpty(sx+1, 0));
        assertFalse(board.isEmpty(sx+2, 0));
        assertFalse(board.isEmpty(sx,   1));
        assertFalse(board.isEmpty(sx+1, 1));

        Tetromino j1 = MockTetromino.of(TetrominoType.J, 90);
        int jx = Math.max(0, zx-1);
        int jy = H-3;
        j1.setPosition(jx, jy);
        while(!board.collides(j1)) {
            jy = jy - 1;
            j1.setPosition(jx, jy);
        }
        jy = jy + 1;
        j1.setPosition(jx, jy);
        board.placeTetromino(j1);
        assertFalse(board.isEmpty(jx,Math.max(2, jy)));
        assertFalse(board.isEmpty(jx,Math.max(3, jy)));

        //colocar una de forma I en x=0
        Tetromino i2 = MockTetromino.of(TetrominoType.I, 90); //vertical
        int i2x = 0;
        int i2y = H-4;
        i2.setPosition(i2x, i2y);
        while(!board.collides(i2)) {
            i2y = i2y - 1;
            i2.setPosition(i2x, i2y);
        }
        i2y = i2y + 1;
        i2.setPosition(i2x, i2y);
        board.placeTetromino(i2);
        assertFalse(board.isEmpty(0,0));
        assertFalse(board.isEmpty(0, 1));
        assertFalse(board.isEmpty(0, 2));
        assertFalse(board.isEmpty(0, 3));

        //Z vertical para colisionar con la S de la derecha
        Tetromino z3 = MockTetromino.of(TetrominoType.Z, 90);
        int z3x = 8;
        int z3y = H-3;
        z3.setPosition(z3x, z3y);
        while(!board.collides(z3)) {
            z3y = z3y - 1;
            z3.setPosition(z3x, z3y);
        }
        assertTrue(board.collides(z3));
    }

    /**
     * comprobar de todo el board, si hay alguna fila completa
     */
    @Test
    void testClearLines() {
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
