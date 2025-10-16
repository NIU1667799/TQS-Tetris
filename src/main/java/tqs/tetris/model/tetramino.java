package tqs.tetris.model;

public class tetramino {
    private char shape;
    private int color;
    private int rotation = 0;

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

}
