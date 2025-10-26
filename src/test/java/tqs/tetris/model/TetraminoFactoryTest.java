package tqs.tetris.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertEquals("I", I.getShape());
        assertEquals(0, I.getColor());

        // Pairwise done with: https://pairwise.teremokgames.com/
        /* gives the 8 * 9 = 72 combinations but it dosn't make sense to test all of them
         * shapes: I, O, T, S, Z, J, L -> I, O, T, S, Z, J, L, X
         * colors: 0, 1, 2, 3, 4, 5 -> MIN, -1, 0, 1, 3 , 4, 5, 6, MAX
         * Sense these values don't interact with each other and they are already teste in
         * tetraminoTest, we will just test a subset of the combinations here.
         */

        tetramino O = factory.createTetramino('O', 3);
        assertEquals("O", O.getShape());
        assertEquals(3, O.getColor());

        tetramino T = factory.createTetramino('T', 5);
        assertEquals("T", T.getShape());
        assertEquals(5, T.getColor());
        // Invalid shape
        try {
            factory.createTetramino('X', 3);
            assertTrue(false);
        } catch (Exception e) { }
        // Invalid color
        try {
            factory.createTetramino('I', 6);
            assertTrue(false);
        } catch (Exception e) { }

    }
}
