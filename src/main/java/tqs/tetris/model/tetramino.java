package tqs.tetris.model;

public class tetramino {
    private char shape;
    private int color;
    private int rotation = 0;

    // First matrix is rotation 0, 
    // Second is rotation 1, etc.
    private static final int[][][][] SHAPES = {
        // I
        {
            {{0,0,0,0}, 
             {1,1,1,1}, 
             {0,0,0,0}, 
             {0,0,0,0}},
             
            {{0,0,1,0},
             {0,0,1,0},
             {0,0,1,0},
             {0,0,1,0}},

            {{0,0,0,0},
             {0,0,0,0},
             {1,1,1,1},
             {0,0,0,0}},
              
            {{0,1,0,0},
             {0,1,0,0},
             {0,1,0,0},
             {0,1,0,0}}
        },
        // O
        {
            {{1,1}, {1,1}},
            {{1,1}, {1,1}},
            {{1,1}, {1,1}},
            {{1,1}, {1,1}}
        },
        // T
        {
            {{0,1,0}, {1,1,1}, {0,0,0}},
            {{0,1,0}, {0,1,1}, {0,1,0}},
            {{0,0,0}, {1,1,1}, {0,1,0}},
            {{0,1,0}, {1,1,0}, {0,1,0}}
        },
        // S
        {
            {{0,1,1}, {1,1,0}, {0,0,0}},
            {{0,1,0}, {0,1,1}, {0,0,1}},
            {{0,0,0}, {0,1,1}, {1,1,0}},
            {{1,0,0}, {1,1,0}, {0,1,0}}
        },
        // Z
        {
            {{1,1,0}, {0,1,1}, {0,0,0}},
            {{0,0,1}, {0,1,1}, {0,1,0}},
            {{0,0,0}, {1,1,0}, {0,1,1}},
            {{0,1,0}, {1,1,0}, {1,0,0}}
        },
        // J
        {
            {{1,0,0}, {1,1,1}, {0,0,0}},
            {{0,1,1}, {0,1,0}, {0,1,0}},
            {{0,0,0}, {1,1,1}, {0,0,1}},
            {{0,1,0}, {0,1,0}, {1,1,0}}
        },
        // L
        {
            {{0,0,1}, {1,1,1}, {0,0,0}},
            {{0,1,0}, {0,1,0}, {0,1,1}},
            {{0,0,0}, {1,1,1}, {1,0,0}},
            {{1,1,0}, {0,1,0}, {0,1,0}}
        }
    };

    public tetramino(char shape, int color) {
        this.shape = shape;
        this.color = color;
    }
    
    public char getShape() {
        return shape;
    }

    public void setShape(char s) {
        assert (s == 'I' || s == 'O' || s == 'T' || s == 'S' || s == 'Z' || s == 'J' || s == 'L') : "Invalid shape";
        this.shape = s;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int c) {
        assert (c >= 0 && c <= 5) : "Invalid color";
        this.color = c;
    }

    public int getRotation() {
        return rotation;
    }

    public void rotate() {
        rotation = (rotation + 1) % 4; 
    }

    private int getShapeIndex() {
        return switch (shape) {
            case 'I' -> 0;
            case 'O' -> 1;
            case 'T' -> 2;
            case 'S' -> 3;
            case 'Z' -> 4;
            case 'J' -> 5;
            case 'L' -> 6;
            default -> 0;
        };
    }

    public int[][] getShapeMatrix() {
        return SHAPES[getShapeIndex()][rotation];
    }

}
