package tqs.tetris.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class TetrominoFactoryTest {

    @Test
    public void tetraminoFactoryConstructionTest() {
        TetrominoFactory factory = new TetrominoFactory();
        assertNotNull(factory);

        // Trying to isolate the Random generator for better testing
        MockRandom mockRandom = new MockRandom(1, 1);
        TetrominoFactory factory2 = new TetrominoFactory(mockRandom);
        assertNotNull(factory2);
        assertEquals(mockRandom, factory2.getRandom());

        // Test setter for generator.
        MockRandom mockRandom2 = new MockRandom(2, 2);
        factory.setRandom(mockRandom2);
        assertEquals(mockRandom2, factory.getRandom());
    }

    @Test
    public void createTetraminoTest() {
        TetrominoFactory factory = new TetrominoFactory();
        Tetromino I = factory.createTetramino('I', 0);
        assertEquals('I', I.getShape());
        assertEquals(0, I.getColor());

        // Pairwise done with: https://pairwise.teremokgames.com/
        /* gives the 8 * 9 = 72 combinations but it dosn't make sense to test all of them
         * shapes: I, O, T, S, Z, J, L -> I, O, T, S, Z, J, L, X
         * colors: 0, 1, 2, 3, 4, 5 -> MIN, -1, 0, 1, 3 , 4, 5, 6, MAX
         * Sense these values don't interact with each other and they are already teste in
         * tetraminoTest, we will just test a subset of the combinations here.
         */
        Tetromino O = factory.createTetramino('O', 3);
        assertEquals('O', O.getShape());
        assertEquals(3, O.getColor());

        Tetromino T = factory.createTetramino('T', 5);
        assertEquals('T', T.getShape());
        assertEquals(5, T.getColor());
        // Invalid shape
        assertThrows(AssertionError.class, () -> factory.createTetramino('X', 3));
        // Invalid color
        assertThrows(AssertionError.class, () -> factory.createTetramino('I', 6));
    }

    @Test
    public void createRandomTetraminoTest() throws IOException {
        try {
            // In this case we will use pariwise to test all combinations of shape and color
            // shapes: I, O, T, S, Z, J, L
            // colors: 0, 1, 2, 3, 4, 5
            File file = new File("src/test/java/tqs/tetris/model/TetraminoFactoryPairwise.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] parts = line.split("\\s+");
                char expectedShape = parts[0].charAt(0);
                int expectedColor = Integer.parseInt(parts[1]);
                int shapeIndex = shapeToIndex(expectedShape);
                MockRandom mock = new MockRandom(shapeIndex, expectedColor);
                TetrominoFactory factory = new TetrominoFactory(mock);
                Tetromino tet = factory.createRandomTetramino();
                assertEquals(expectedShape, tet.getShape());
                assertEquals(expectedColor, tet.getColor());
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.err.println("TetraminoFactoryPairwise.txt file not found. Skipping createRandomTetraminoTest.");
        }
    }

    private int shapeToIndex(char shape) {
        switch (shape) {
            case 'I': return 0;
            case 'O': return 1;
            case 'T': return 2;
            case 'S': return 3;
            case 'Z': return 4;
            case 'J': return 5;
            case 'L': return 6;
            default: throw new IllegalArgumentException("Unknown shape: " + shape);
        }
    }
}
