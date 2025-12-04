package tqs.tetris.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
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
    y=0 | (0,0) (1,0) (2,0) (3,0) (4,0)   <-- fila inferior
        x=0   x=1   x=2   x=3   x=4
 */

/*
class MockTetromino implements Tetramino {
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
    
    /**
     * invariant case, all cells are empty on a new board.
     * statement coverage of isEmpty().
     */

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
     * make them all fall until colliding with the floor or with already placed pieces
     * then check that the final occupied cells are as expected
     * 
     * type of test:
     * valors limits --> pieces colliding with floor and walls
     * caixa blanca --> multiple paths of board.collides() and board.placeTetromino()
     */

    @Test
    void testColides(){
        int W = board.getWidth();
        int H = board.getHeight();

        //O shape in the center of the board, fall until collides with floor
        Tetromino oCenter = new Tetromino('O', 1);
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
        assertFalse(board.isEmpty(ox, oy+1));
        assertFalse(board.isEmpty(ox+1, oy+1));

        //I shape vertical colliding with O shape, should stop on top of O shape
        Tetromino iVertical = new Tetromino('I', 2);
        iVertical.rotate();
        int ix = ox - 2; //same column as O shape
        int iy = H-1;
        iVertical.setPosition(ix, iy);
        while(!board.collides(iVertical)) {
            iVertical.setPosition(ix, iy--);
        }
        iy = iy +1; //last non colliding
        iVertical.setPosition(ix, iy);
        board.placeTetromino(iVertical);
        // Verificamos que haya caido encima de O (ox)
        // ix + 2 = ox
        assertFalse(board.isEmpty(ox, oy + 2));

        Tetromino o2 = new Tetromino('O', 3);
        int ox2 = ox;
        int oy2 = oy+3;
        o2.setPosition(ox2, oy2); //same column as I shape
        assertTrue(board.collides(o2));

        while (!board.collides(o2)) {
            oy2 = oy2 + 1; // Subimos en lugar de bajar porque empezamos chocando
            o2.setPosition(ox2, oy2);
        }
        board.placeTetromino(o2);

        Tetromino z1 = new Tetromino('Z', 4);
        z1.setPosition(ox-1, 2);
        assertTrue(board.collides(z1));

        Tetromino z2 = new Tetromino('Z', 5);
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
        assertFalse(board.isEmpty(zx, 1));
        assertFalse(board.isEmpty(zx+1, 1));
        assertFalse(board.isEmpty(zx+1, 0));
        assertFalse(board.isEmpty(zx+2, 0));

        Tetromino s1 = new Tetromino('S', 1);
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
        // La S caera sobre la pila de O e I en el lado izquierdo de la S.
        assertFalse(board.isEmpty(sx+1, sy+1));

        Tetromino j1 = new Tetromino('J', 2);
        j1.rotate();
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
        assertFalse(board.isEmpty(jx+1, jy+1));

        //colocar una de forma I en x=0
        Tetromino i2 = new Tetromino('I', 3);
        i2.rotate();
        int i2x = -2;
        int i2y = H-4;
        i2.setPosition(i2x, i2y);
        while(!board.collides(i2)) {
            i2y = i2y - 1;
            i2.setPosition(i2x, i2y);
        }
        i2y = i2y + 1;
        i2.setPosition(i2x, i2y);
        board.placeTetromino(i2);
        assertTrue(board.isEmpty(0,0));
        assertFalse(board.isEmpty(0, 1));
        assertFalse(board.isEmpty(0, 2));

        //Z vertical para colisionar con la S de la derecha
        Tetromino z3 = new Tetromino('Z', 4);
        z3.rotate();
        int z3x = 8;
        int z3y = H-3;
        z3.setPosition(z3x, z3y);
        while(!board.collides(z3)) {
            z3y = z3y - 1;
            z3.setPosition(z3x, z3y);
        }
        assertTrue(board.collides(z3) || z3y >= 0);
    }

    /**
     * test for line clearing, no full rows, one full row, two consecutive full rows
     * the value returned means the number of cleared rows
     * type of test:
     * caixa blanca --> multiple paths of board.clearLines()
     * particio equivalent --> 0 lines, 1 line, 2 lines
     */
    @Test
    void testClearLines() {
        int cleared = board.clearLines();
        assertEquals(0, cleared);

        // fill the bottom row (y=0)
        for (int x = 0; x < board.getWidth(); x++) 
            board.setCell(x, 0, true);
        cleared = board.clearLines();
        assertEquals(1, cleared);
        // should be empty
        for (int x = 0; x < board.getWidth(); x++) {
            assertTrue(board.isEmpty(x, 0));
        }

        //fil two rows (y=0, y=1)
        for (int x = 0; x < board.getWidth(); x++) {
            board.setCell(x, 0, true);
            board.setCell(x, 1, true);
        }
        cleared = board.clearLines();
        assertEquals(2, cleared);

        //both rows should be empty after clearing
        for (int x = 0; x < board.getWidth(); x++) {
            assertTrue(board.isEmpty(x, 0));
            assertTrue(board.isEmpty(x, 1));
        }
    }

    /**
     * This test will check the limit cases for line clearing
     * test the top row
     * type of test:
     * valors limits --> top row index
     * caixa blanca --> path of board.clearLines() that clears the top row
     */
    @Test
    void testClearLinesBoundary() {
        int topRow = board.getHeight()-1;

        //fill the whole top row
        for(int i = 0; i < board.getWidth();i++){
            board.setCell(i, topRow, true);
        }

        //precondition check
        assertFalse(board.isEmpty(0, topRow));
        int cleared = board.clearLines();
        assertEquals(1, cleared);

        //postcondition check, empty after clearing
        assertTrue(board.isEmpty(0, topRow));
    }

    @Test
    void testInvalidBoardSize() {
        // boundary case, invalid partition: width <= 0
        assertThrows(IllegalArgumentException.class, () -> new Board(0, 10));
        assertThrows(IllegalArgumentException.class, () -> new Board(-1, 20));

        // boundary case, invalid partition: height <= 0
        assertThrows(IllegalArgumentException.class, () -> new Board(10, 0));
        assertThrows(IllegalArgumentException.class, () -> new Board(10, -5));
    }

    @Test
    void testIsEmptyOutOfBounds() {
        Board board = new Board(10, 20);
        assertFalse(board.isEmpty(-1, 0));
        assertFalse(board.isEmpty(10, 0));
        assertFalse(board.isEmpty(0, -1));
        assertFalse(board.isEmpty(0, 20));
    }
}
