package tqs.tetris.model;

public class Board {
    private final int width = 10;
    private final int height = 20;
    private int[][] grid;

    public Board(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Invalid board size");
        }
        this.grid = new int[height][width]; // 0 for empty, 1 for filled
    }
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }

    public boolean isEmpty(int x, int y) {
        return grid[y][x] == 0;
    }

    public boolean setCell(int x, int y, boolean filled) {
        if(y<0||y>=height||x<0||x>=width) {
            return false;
        }
        grid[y][x] = filled ? 1 : 0;
        return true;
    }

    /**
     * Returns the number of cleared rows.
     * bottom row --> y=0
     */
    public int clearLines() {
        int cleared = 0;
        for (int y = 0; y < height; y++) {
            boolean full = true;
            for (int x = 0; x < width; x++) {
                if (grid[y][x] == 0) { full = false; break; }
            }
            if (full) {
                cleared++;
                // shift rows down
                for (int yy = y; yy < height - 1; yy++) {
                    for (int x = 0; x < width; x++) {
                        grid[yy][x] = grid[yy+1][x];
                    }
                }
                // clear topmost row
                for (int x = 0; x < width; x++) grid[height-1][x] = 0;
                y--;
            }
        }
        return cleared;
    }
}
