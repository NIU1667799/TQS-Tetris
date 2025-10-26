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

public class TetraminoFactoryTest {

    @Test
    public void tetraminoFactoryConstructionTest() {
        tetraminoFactory factory = new tetraminoFactory();
        assertNotNull(factory);

        // Trying to isolate the Random generator for better testing
        mockRandom mockRandom = new mockRandom(1, 1);
        tetraminoFactory factory2 = new tetraminoFactory(mockRandom);
        assertNotNull(factory2);
        assertEquals(mockRandom, factory2.getRandom());

        // Test setter for generator.
        mockRandom mockRandom2 = new mockRandom(2, 2);
        factory.setRandom(mockRandom2);
        assertEquals(mockRandom2, factory.getRandom());
    }

    @Test
    public void createTetraminoTest() {
        tetraminoFactory factory = new tetraminoFactory();
        tetramino I = factory.createTetramino('I', 0);
        assertEquals('I', I.getShape());
        assertEquals(0, I.getColor());

        // Pairwise done with: https://pairwise.teremokgames.com/
        /* gives the 8 * 9 = 72 combinations but it dosn't make sense to test all of them
         * shapes: I, O, T, S, Z, J, L -> I, O, T, S, Z, J, L, X
         * colors: 0, 1, 2, 3, 4, 5 -> MIN, -1, 0, 1, 3 , 4, 5, 6, MAX
         * Sense these values don't interact with each other and they are already teste in
         * tetraminoTest, we will just test a subset of the combinations here.
         */
        tetramino O = factory.createTetramino('O', 3);
        assertEquals('O', O.getShape());
        assertEquals(3, O.getColor());

        tetramino T = factory.createTetramino('T', 5);
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
                mockRandom mock = new mockRandom(shapeIndex, expectedColor);
                tetraminoFactory factory = new tetraminoFactory(mock);
                tetramino tet = factory.createRandomTetramino();
                assertEquals(expectedShape, tet.getShape());
                assertEquals(expectedColor, tet.getColor());
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.err.println("TetraminoFactoryPairwise.txt file not found. Skipping createRandomTetraminoTest.");
        }
    }

    private int shapeToIndex(char shape) {
        return switch (shape) {
            case 'I' -> 0;
            case 'O' -> 1;
            case 'T' -> 2;
            case 'S' -> 3;
            case 'Z' -> 4;
            case 'J' -> 5;
            case 'L' -> 6;
            default -> throw new IllegalArgumentException("Unknown shape: " + shape);
        };
    }
}
