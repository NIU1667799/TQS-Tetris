package tqs.tetris.model;

public class Game {
    Board board;
    private TetrominoFactory tetrominoFactory;
    private GameState gameState;

    public Game() {
        this.board = new Board(10, 20);
        this.tetrominoFactory = new TetrominoFactory();
        this.gameState = GameState.RUNNING;
    }

    public Game(Board board, TetrominoFactory tetrominoFactory, GameState gameState) {
        this.board = board;
        this.tetrominoFactory = tetrominoFactory;
        this.gameState = gameState;
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
}
