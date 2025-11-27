package tqs.tetris.model;

import java.util.ArrayList;

public class MockBoard extends Board {
    private ArrayList<Integer> boardArray;

    public MockBoard(int width, int height) {
        super(width, height);
        this.boardArray = new ArrayList<>(width * height);
        for (int i = 0; i < width * height; i++) {
            this.boardArray.add(0);
        }
    }

    public ArrayList<Integer> getBoardArray() {
        return boardArray;
    }
}
