package tqs.tetris.model;

public class Board {
    private final int width;
    private final int height;
    private int[][] grid;

    public Board(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Invalid board size");
        }
        this.width = width;
        this.height = height;
        this.grid = new int[height][width]; // 0 for empty, 1 for filled
    }
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }

    /**
     * Check if cell is empty
     * @param x column
     * @param y row
     * @return
     * false if out of bounds
     * true if empty
     * false if filled
     */
    public boolean isEmpty(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        return grid[y][x] == 0;
    }

    /**
     * Set cell to filled or empty
     * @param x column
     * @param y row
     * @param filled true to fill, false to empty
     * @return
     * false if out of bounds, true if successful
     */
    public boolean setCell(int x, int y, boolean filled) {
        if(y<0||y>=height||x<0||x>=width) {
            return false;
        }
        grid[y][x] = filled ? 1 : 0;
        return true;
    }

    /**
     * Check if tetromino collides with filled cells or out of bounds
     * @param tetromino the tetromino to check
     * @return
     * true if collides, false otherwise
     * the collision condition is:
     * when any cell of the tetromino is out of bounds
     * or when any cell of the tetromino overlaps a filled cell in the board
     */
    public boolean collides(Tetromino tetromino) {
        int[][] cells = tetromino.getCells();

        for(int[] cell:cells){
            int x = cell[0];
            int y = cell[1];
            if(x < 0 || x >= width){
                return true;
            }
            if(y < 0){
                return true;
            }
            if(y < height && grid[y][x] != 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Place tetromino on the board (set its cells to filled)
     * @param tetromino
     * for each cell of the tetromino, if it lies inside the board, set it to filled --> 1
     * and ignore cells that are outside theboard
     */
    public void placeTetromino(Tetromino tetromino) {
        int[][] cells = tetromino.getCells();
        for (int[] cell : cells) {
            int x = cell[0];
            int y = cell[1];
            if (y >= 0 && y < height && x >= 0 && x < width) {
                grid[y][x] = 1;
            }
        }
    }

    /**
     * Returns the number of cleared rows.
     * bottom row --> y=0
     * scan each row from bottom to top, a row is full if all its cells are filled --> 1
     * when a row is full, remove it and shift all rows above it down
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
