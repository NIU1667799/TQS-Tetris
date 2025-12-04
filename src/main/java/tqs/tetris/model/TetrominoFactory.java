package tqs.tetris.model;

import java.util.Random;

/**
 * this class creates tetrominoes with random shapes and colors, 
 * or creates with specific shape and color
 */
public class TetrominoFactory {
    private static final char[] SHAPES = {'I', 'O', 'T', 'S', 'Z', 'J', 'L'};
    private static final int COLOR_RANGE = 6;
    private Random random;

    public TetrominoFactory() {
        this.random = new Random();
    }

    public TetrominoFactory(Random random) {
        this.random = random;
    }

    public Tetromino createRandomTetramino() {
        int shapeIndex = random.nextInt(SHAPES.length);
        int color = random.nextInt(COLOR_RANGE);
        return createTetramino(SHAPES[shapeIndex], color);
    }

    public Tetromino createTetramino(char shape, int color) {
        assert(color >= 0 && color < COLOR_RANGE): "Invalid color: " + color;
        assert(shape == 'I' || shape == 'O' || shape == 'T' || shape == 'S' ||
            shape == 'Z' || shape == 'J' || shape == 'L'): "Invalid shape: " + shape;
        return new Tetromino(shape, color);
    }

    public void setRandom(Random random) {
        this.random = random;
    }
    public Random getRandom() {
        return this.random;
    }
}


