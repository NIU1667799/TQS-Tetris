package tqs.tetris.model;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class TetraminoFactoryTest {
    
    @Test
    public void tetraminoFactoryConstructionTest() {
        tetraminoFactory factory = new tetraminoFactory();
        assertNotNull(factory);

        // Trying to isolate the Random generator for better testing
        mockRandomGenerator mockRandomGenerator = new mockRandomGenerator();
        tetraminoFactrory factory2 = new tetraminoFactory(mockRandomGenerator);
        assertNotNull(factory2);
        assertEquals(mockRandomGenerator, factory2.getRandomGenerator()); 
        
        // Test setter for generator.
        mockRandomGenerator mockRandomGenerator2 = new mockRandomGenerator();
        factory.setRandomGenerator(mockRandomGenerator2);
        assertEquals(mockRandomGenerator2, factory.getRandomGenerator());
    }
}
