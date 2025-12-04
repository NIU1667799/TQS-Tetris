package tqs.tetris.model;

public class Game {
    Board board;
    private TetrominoFactory tetrominoFactory;
    private GameState gameState;
    private Tetromino current;

    public Game() {
        this.board = new Board(10, 20);
        this.tetrominoFactory = new TetrominoFactory();
        this.gameState = GameState.RUNNING; // default state
        spawnNewPiece();
    }

    public Game(Board board, TetrominoFactory tetrominoFactory, GameState gameState) {
        this.board = board;
        this.tetrominoFactory = tetrominoFactory;
        this.gameState = gameState;
        spawnNewPiece();
    }

    /**
     * Spawn a new tetromino at the top of the board
     * use tetrominoFactory to create a random tetromino, positions the piece horizontally centered at the top of the board
     * if collides, set game state to GAME_OVER
     */
    private void spawnNewPiece() {
        current = tetrominoFactory.createRandomTetramino();
        current.setPosition(board.getWidth() / 2 - 1, board.getHeight() - 1);
        if (board.collides(current)) {
            gameState = GameState.GAME_OVER;
        }
    }

    /**
     * if the game is not playable, then dont do anything
     * and otherwise, move the current piece one row down, 
     * if it collides, move the piece back up, lock it on the board, clear lines, and spawn a new piece
     */
    public void tick() {
        if (!gameState.isPlayable()) {
            return;
        }
        // Move current piece down by 1
        current.setPosition(current.getX(), current.getY() - 1);
        if (board.collides(current)) {
            current.setPosition(current.getX(), current.getY() + 1);
            board.placeTetromino(current);
            board.clearLines();
            spawnNewPiece();
        }
    }

    public void resume(){
        this.gameState = GameState.RUNNING;
    }

    public void pause(){
        this.gameState = GameState.PAUSED;
    }

    public void gameOver(){
        this.gameState = GameState.GAME_OVER;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public TetrominoFactory getTetrominoFactory() {
        return tetrominoFactory;
    }

    public void setTetrominoFactory(TetrominoFactory tetrominoFactory) {
        this.tetrominoFactory = tetrominoFactory;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Tetromino getCurrent() {
        return current;
    }

    public void moveLeft() {
        current.setPosition(current.getX() - 1, current.getY());
        if (board.collides(current)) {
            current.setPosition(current.getX() + 1, current.getY());
        }
    }

    public void moveRight() {
        current.setPosition(current.getX() + 1, current.getY());
        if (board.collides(current)) {
            current.setPosition(current.getX() - 1, current.getY());
        }
    }

    public void rotateCurrent() {
        current.rotate();
        if (board.collides(current)) {
            // if collides undo rotation to not glitch
            current.rotate();
            current.rotate();
            current.rotate();
        }
    }

}
