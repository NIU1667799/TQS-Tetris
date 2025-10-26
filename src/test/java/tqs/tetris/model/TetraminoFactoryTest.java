package tqs.tetris.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
}
