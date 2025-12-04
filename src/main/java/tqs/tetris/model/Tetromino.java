package tqs.tetris.model;
import java.util.ArrayList;
import java.util.List;

public class Tetromino {
    private char shape;
    private int color;
    private int rotation = 0;

    //board position
    private int x = 0;
    private int y = 0;

    // First matrix is rotation 0, 
    // Second is rotation 1, etc.
    //SHAPES[shapeIndex][rotation][row][col]
    // shapeIndex: 0->I, 1->O, 2->T, 3->S, 4->Z, 5->J, 6->L
    // rotation: 0,1,2,3 --> 0, 90, 180, 270 degress
    // each matrix isthe shape
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

    public Tetromino(char shape, int color) {
        this.shape = shape;
        this.color = color;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * this method computes the board coordinates of the occupied cells of the tetromino
     * for each cell with value 1, compute its board coordinates based on the tetromino position (x,y)
     * and add it to the list of occupied cells, offset by the tetromino position
     */
    public int[][] getCells() {
        int[][] matrix = getShapeMatrix();
        List<int[]> occupied = new ArrayList<>();
        int h = matrix.length;
        for(int row = 0; row < h ; row++) {
            for(int col = 0; col < matrix[row].length; col++){
                if(matrix[row][col] == 1){
                    int X = this.x+col;
                    int Y=this.y+(h-1-row);
                    occupied.add(new int[]{X,Y});
                }
            }
        }

        //convert the array list to int[][]
        int[][] cells = new int[occupied.size()][2];
        for(int i=0;i<occupied.size();i++){
            cells[i]=occupied.get(i);
        }
        return cells;
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
        switch (shape) {
            case 'I': return 0;
            case 'O': return 1;
            case 'T': return 2;
            case 'S': return 3;
            case 'Z': return 4;
            case 'J': return 5;
            case 'L': return 6;
            default: return 0;
        }
    }

    public int[][] getShapeMatrix() {
        return SHAPES[getShapeIndex()][rotation];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
