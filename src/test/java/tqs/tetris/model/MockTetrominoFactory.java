package tqs.tetris.model;

public class MockTetrominoFactory extends TetrominoFactory {
    private final char fixedShape;
    private final int fixedColor;

    public MockTetrominoFactory(char shape, int color) {
        this.fixedShape = shape;
        this.fixedColor = color;
    }

    @Override
    public Tetromino createRandomTetramino() {
        return new Tetromino(fixedShape, fixedColor);
    }
}
