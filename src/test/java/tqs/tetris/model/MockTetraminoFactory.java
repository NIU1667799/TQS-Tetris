package tqs.tetris.model;

public class MockTetraminoFactory extends tetraminoFactory {
    private final char fixedShape;
    private final int fixedColor;

    public MockTetraminoFactory(char shape, int color) {
        this.fixedShape = shape;
        this.fixedColor = color;
    }

    @Override
    public tetramino createRandomTetramino() {
        return new tetramino(fixedShape, fixedColor);
    }
}
