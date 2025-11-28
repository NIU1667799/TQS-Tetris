package tqs.tetris.model;

public class Game {
    Board board;
    private TetrominoFactory tetrominoFactory;
    private GameState gameState;
    private Tetromino current;

    public Game() {
        this.board = new Board(10, 20);
        this.tetrominoFactory = new TetrominoFactory();
        this.gameState = GameState.RUNNING;
        spawnNewPiece();
    }

    public Game(Board board, TetrominoFactory tetrominoFactory, GameState gameState) {
        this.board = board;
        this.tetrominoFactory = tetrominoFactory;
        this.gameState = gameState;
        spawnNewPiece();
    }

    private void spawnNewPiece() {
        current = tetrominoFactory.createRandomTetramino();
        current.setPosition(board.getWidth() / 2 - 1, board.getHeight() - 1);
        if (board.collides(current)) {
            gameState = GameState.GAME_OVER;
        }
    }

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

}
