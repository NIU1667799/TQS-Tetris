package tqs.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/*
 * Tetromino
 * Represents a falling piece
 * Knows its shape, rotation states, color, and current position
 * Can move left, right, and down
 * Can rotate
 */
public class TetrominoTest {
    
    @Test
    public void testTetrominoInitialization() {
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
        */

        // Test valid color values
        tetramino tetromino0 = new tetramino('T', 0);
        assertEquals(0, tetromino0.getColor());

        tetramino tetromino1 = new tetramino('T', 1);
        assertEquals(1, tetromino1.getColor());
        
        tetramino tetromino3 = new tetramino('T', 3);
        assertEquals(3, tetromino3.getColor()); 
        
        tetramino tetromino4 = new tetramino('T', 4);
        assertEquals(4, tetromino4.getColor());
        
        tetramino tetromino5 = new tetramino('T', 5);
        assertEquals(5, tetromino5.getColor());

        // Test valid shapes
        tetramino tetrominoI = new tetramino('I', 0);
        assertEquals('I', tetrominoI.getShape());
        
        tetramino tetrominoO = new tetramino('O', 0);
        assertEquals('O', tetrominoO.getShape());
        
        tetramino tetrominoT = new tetramino('T', 0);
        assertEquals('T', tetrominoT.getShape());
        
        tetramino tetrominoS = new tetramino('S', 0);
        assertEquals('S', tetrominoS.getShape());
        
        tetramino tetrominoZ = new tetramino('Z', 0);
        assertEquals('Z', tetrominoZ.getShape());
        
        tetramino tetrominoJ = new tetramino('J', 0);
        assertEquals('J', tetrominoJ.getShape());
        
        tetramino tetrominoL = new tetramino('L', 0);
        assertEquals('L', tetrominoL.getShape());
    }

    @Test
    public void testTetrominoSettersAndGetters() {
        tetramino tetramino = new tetramino('I', 0);
        
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
}