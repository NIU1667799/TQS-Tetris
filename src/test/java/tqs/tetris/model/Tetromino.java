package tqs.tetris.model;

//class tetromino to be used as a mockobject

public interface Tetromino {
    void setPosition(int x, int y);
    int[][] getCells();
}
