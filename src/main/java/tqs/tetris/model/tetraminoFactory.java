package tqs.tetris.model;

import java.util.Random;

public class tetraminoFactory {
    private static final char[] SHAPES = {'I', 'O', 'T', 'S', 'Z', 'J', 'L'};
    private static final int COLOR_RANGE = 6;
    private Random random;

    public tetraminoFactory() {
        this.random = new Random();
    }

    public tetraminoFactory(Random random) {
        this.random = random;
    }

    public tetramino createRandomTetramino() {
        return null;
    }

    public tetramino createTetramino(char shape, int color) {
        assert(color >= 0 && color < COLOR_RANGE): "Invalid color: " + color;
        assert(shape == 'I' || shape == 'O' || shape == 'T' || shape == 'S' ||
               shape == 'Z' || shape == 'J' || shape == 'L'): "Invalid shape: " + shape;
        return new tetramino(shape, color);
    }

    public void setRandom(Random random) {
       this.random = random;
    }
    public Random getRandom() {
        return this.random;
    }
}


