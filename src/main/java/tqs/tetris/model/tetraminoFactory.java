package tqs.tetris.model;

import java.util.random.RandomGenerator;

public class tetraminoFactory {
    private static final char[] SHAPES = {'I', 'O', 'T', 'S', 'Z', 'J', 'L'};
    private static final int COLOR_RANGE = 6;
    private RandomGenerator random;

    public tetraminoFactory() {
    }

    public tetraminoFactory(RandomGenerator random) {
        this.random = random;
    }

    private tetramino createRandomTetramino() {
        return null;
    }

    private tetramino createTetramino(char shape, int color) {
        return null;
    }

    public void setRandomGenerator(RandomGenerator random) {
       this.random = random;
    }
}


