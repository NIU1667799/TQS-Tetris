package tqs.tetris.model;

import java.util.Random;

public class tetraminoFactory {
    private static final char[] SHAPES = {'I', 'O', 'T', 'S', 'Z', 'J', 'L'};
    private static final int COLOR_RANGE = 6;
    private Random random;

    public tetraminoFactory() {
    }

    public tetraminoFactory(Random random) {
        this.random = random;
    }

    private tetramino createRandomTetramino() {
        return null;
    }

    private tetramino createTetramino(char shape, int color) {
        return null;
    }

    public void setRandom(Random random) {
       this.random = random;
    }
    public Random getRandom() {
        return this.random;
    }
}


