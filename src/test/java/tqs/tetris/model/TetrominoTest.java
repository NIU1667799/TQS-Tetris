package tqs.tetris.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/*
 * Tetromino
 * Represents a falling piece
 * Knows its shape, rotation states, color, etc
 * Can move left, right, and down
 * Can rotate
 */
public class TetrominoTest {
    
    /* We decided that shapes will be represented by a char, 
        while the color will be represented by a value from 0 to 5 where:
        0 -> red
        1 -> green
        2 -> blue
        3 -> yellow 
        4 -> magenta
        5 -> cyan
        color -> MIN, -1, 0, 1, 3, 4, 5, 6, MAX
        shapes -> 'I', 'O', 'T', 'S', 'Z', 'J', 'L'

        type of test:
        valors limits --> color min and max values, shape valid values
    */
    @Test
    public void testTetrominoInitialization() {
        // Test valid color values
        Tetromino tetromino0 = new Tetromino('T', 0);
        assertEquals(0, tetromino0.getColor());

        Tetromino tetromino1 = new Tetromino('T', 1);
        assertEquals(1, tetromino1.getColor());
        
        Tetromino tetromino3 = new Tetromino('T', 3);
        assertEquals(3, tetromino3.getColor()); 
        
        Tetromino tetromino4 = new Tetromino('T', 4);
        assertEquals(4, tetromino4.getColor());
        
        Tetromino tetromino5 = new Tetromino('T', 5);
        assertEquals(5, tetromino5.getColor());

        // Test valid shapes
        Tetromino tetrominoI = new Tetromino('I', 0);
        assertEquals('I', tetrominoI.getShape());
        
        Tetromino tetrominoO = new Tetromino('O', 0);
        assertEquals('O', tetrominoO.getShape());
        
        Tetromino tetrominoT = new Tetromino('T', 0);
        assertEquals('T', tetrominoT.getShape());
        
        Tetromino tetrominoS = new Tetromino('S', 0);
        assertEquals('S', tetrominoS.getShape());
        
        Tetromino tetrominoZ = new Tetromino('Z', 0);
        assertEquals('Z', tetrominoZ.getShape());
        
        Tetromino tetrominoJ = new Tetromino('J', 0);
        assertEquals('J', tetrominoJ.getShape());
        
        Tetromino tetrominoL = new Tetromino('L', 0);
        assertEquals('L', tetrominoL.getShape());

    }

    @Test
    public void testTetrominoSettersAndGetters() {
        Tetromino tetramino = new Tetromino('I', 0);
        
        // Test shape setters and getters
        tetramino.setShape('I');
        assertEquals('I', tetramino.getShape());
        
        tetramino.setShape('O');
        assertEquals('O', tetramino.getShape());
        
        tetramino.setShape('T');
        assertEquals('T', tetramino.getShape());
        
        tetramino.setShape('S');
        assertEquals('S', tetramino.getShape());
        
        tetramino.setShape('Z');
        assertEquals('Z', tetramino.getShape());
        
        tetramino.setShape('J');
        assertEquals('J', tetramino.getShape());
        
        tetramino.setShape('L');
        assertEquals('L', tetramino.getShape());
        
        // Test color setters and getters
        tetramino.setColor(0);
        assertEquals(0, tetramino.getColor());
        
        tetramino.setColor(1);
        assertEquals(1, tetramino.getColor());
        
        tetramino.setColor(5);
        assertEquals(5, tetramino.getColor());
    }

    @Test
    public void testTetrominoRotation(){
        /* Test rotation
         * Initial rotation state is 
         *  0 -> 0 degrees
         *  1 -> 90 degrees
         *  2 -> 180 degrees
         *  3 -> 270 degrees
         */
        Tetromino tetramino = new Tetromino('I', 0);
        assertEquals(0, tetramino.getRotation());
        tetramino.rotate();
        assertEquals(1, tetramino.getRotation());
        tetramino.rotate();
        assertEquals(2, tetramino.getRotation());
        tetramino.rotate();
        assertEquals(3, tetramino.getRotation());
        tetramino.rotate();
        assertEquals(0, tetramino.getRotation());
    }

    @Test
    public void testMatrixShape(){
        // Test to see if each Tetramino returns it's correct matrix shape
        // shapes -> 'I', 'O', 'T', 'S', 'Z', 'J', 'L'
        Tetromino tetraminoI = new Tetromino('I', 0);
        Tetromino tetraminoO = new Tetromino('O', 1);
        Tetromino tetraminoT = new Tetromino('T', 2);
        Tetromino tetraminoS = new Tetromino('S', 3);
        Tetromino tetraminoZ = new Tetromino('Z', 4);
        Tetromino tetraminoJ = new Tetromino('J', 5);
        Tetromino tetraminoL = new Tetromino('L', 6);
        
        int[][] shapeI = {
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };

        int[][] shapeO = {
            {1, 1},
            {1, 1}
        };

        int[][] shapeT = {
            {0, 1, 0},
            {1, 1, 1},
            {0, 0, 0}
        };
        
        int[][] shapeS = {
            {0, 1, 1},
            {1, 1, 0},
            {0, 0, 0}
        };

        int[][] shapeZ = {
            {1, 1, 0},
            {0, 1, 1},
            {0, 0, 0}
        };

        int[][] shapeJ = {
            {1, 0, 0},
            {1, 1, 1},
            {0, 0, 0}
        };

        int[][] shapeL = {
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 0}
        };

        assertArrayEquals(shapeI, tetraminoI.getShapeMatrix());
        assertArrayEquals(shapeO, tetraminoO.getShapeMatrix());
        assertArrayEquals(shapeT, tetraminoT.getShapeMatrix());
        assertArrayEquals(shapeS, tetraminoS.getShapeMatrix());
        assertArrayEquals(shapeZ, tetraminoZ.getShapeMatrix());
        assertArrayEquals(shapeJ, tetraminoJ.getShapeMatrix());
        assertArrayEquals(shapeL, tetraminoL.getShapeMatrix());
    
        // I rotation tests
        int[][] shapeI2 = {
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0}
        };
        tetraminoI.rotate();
        assertArrayEquals(shapeI2, tetraminoI.getShapeMatrix());

        int[][] shapeI3 = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0}
        };
        tetraminoI.rotate();
        assertArrayEquals(shapeI3, tetraminoI.getShapeMatrix());

        int[][] shapeI4 = {
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0}
        };
        tetraminoI.rotate();
        assertArrayEquals(shapeI4, tetraminoI.getShapeMatrix());

        // O rotation tests (should remain the same)
        int [][] shapeO2 = {
            {1, 1},
            {1, 1}
        };
        tetraminoO.rotate();
        assertArrayEquals(shapeO2, tetraminoO.getShapeMatrix());

        tetraminoO.rotate();
        assertArrayEquals(shapeO2, tetraminoO.getShapeMatrix());

        tetraminoO.rotate();
        assertArrayEquals(shapeO2, tetraminoO.getShapeMatrix());

        tetraminoO.rotate();
        assertArrayEquals(shapeO2, tetraminoO.getShapeMatrix());

        // T rotation tests
        int[][] shapeT2 = {
            {0, 1, 0},
            {0, 1, 1},
            {0, 1, 0}
        };
        tetraminoT.rotate();
        assertArrayEquals(shapeT2, tetraminoT.getShapeMatrix());

        int[][] shapeT3 = {
            {0, 0, 0},
            {1, 1, 1},
            {0, 1, 0}
        };
        tetraminoT.rotate();
        assertArrayEquals(shapeT3, tetraminoT.getShapeMatrix());

        int[][] shapeT4 = {
            {0, 1, 0},
            {1, 1, 0},
            {0, 1, 0}
        };
        tetraminoT.rotate();
        assertArrayEquals(shapeT4, tetraminoT.getShapeMatrix());

        // S rotation tests
        int[][] shapeS2 = {
            {0, 1, 0},
            {0, 1, 1},
            {0, 0, 1}
        };
        tetraminoS.rotate();
        assertArrayEquals(shapeS2, tetraminoS.getShapeMatrix());

        int[][] shapeS3 = {
            {0, 0, 0},
            {0, 1, 1},
            {1, 1, 0}
        };
        tetraminoS.rotate();
        assertArrayEquals(shapeS3, tetraminoS.getShapeMatrix());

        int[][] shapeS4 = {
            {1, 0, 0},
            {1, 1, 0},
            {0, 1, 0}
        };
        tetraminoS.rotate();
        assertArrayEquals(shapeS4, tetraminoS.getShapeMatrix());

        int[][] shapeZ2 = {
            {0, 0, 1},
            {0, 1, 1},
            {0, 1, 0}
        };
        tetraminoZ.rotate();
        assertArrayEquals(shapeZ2, tetraminoZ.getShapeMatrix());
        
        int[][] shapeZ3 = {
            {0, 0, 0},
            {1, 1, 0},
            {0, 1, 1}
        };
        tetraminoZ.rotate();
        assertArrayEquals(shapeZ3, tetraminoZ.getShapeMatrix());

        int[][] shapeZ4 = {
            {0, 1, 0},
            {1, 1, 0},
            {1, 0, 0}
        };
        tetraminoZ.rotate();
        assertArrayEquals(shapeZ4, tetraminoZ.getShapeMatrix());

        int[][] shapeJ2 = {
            {0, 1, 1},
            {0, 1, 0},
            {0, 1, 0}
        };
        tetraminoJ.rotate();
        assertArrayEquals(shapeJ2, tetraminoJ.getShapeMatrix());
        int[][] shapeJ3 = {
            {0, 0, 0},
            {1, 1, 1},
            {0, 0, 1}
        };
        tetraminoJ.rotate();
        assertArrayEquals(shapeJ3, tetraminoJ.getShapeMatrix());
        int[][] shapeJ4 = {
            {0, 1, 0},
            {0, 1, 0},
            {1, 1, 0}
        };
        tetraminoJ.rotate();
        assertArrayEquals(shapeJ4, tetraminoJ.getShapeMatrix());
        int[][] shapeL2 = {
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 1}
        };
        tetraminoL.rotate();
        assertArrayEquals(shapeL2, tetraminoL.getShapeMatrix());
        int[][] shapeL3 = {
            {0, 0, 0},
            {1, 1, 1},
            {1, 0, 0}
        };
        tetraminoL.rotate();
        assertArrayEquals(shapeL3, tetraminoL.getShapeMatrix());
        int[][] shapeL4 = {
            {1, 1, 0},
            {0, 1, 0},
            {0, 1, 0}
        };
        tetraminoL.rotate();
        assertArrayEquals(shapeL4, tetraminoL.getShapeMatrix());
        
    }
}