package tqs.tetris.model;

import java.util.Random;

public class MockRandom extends Random {
    private int fixedShape;
    private int fixedColor;

    public MockRandom(int shape, int color) {
        this.fixedShape = shape;
        this.fixedColor = color;
    }

    @Override
    public int nextInt(int bound) {
        if (bound == 7) {
            return fixedShape;
        } else if (bound == 6) {
            return fixedColor;
        }
        return 0;
    }

}
