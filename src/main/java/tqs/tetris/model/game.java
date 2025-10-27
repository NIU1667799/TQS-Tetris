package tqs.tetris.model;

public class Game {
    private board board;
    private tetraminoFactory tetraminoFactory;
    private GameState gameState;

    public Game() {
        this.board = new board();
        this.tetraminoFactory = new tetraminoFactory();
        this.gameState = GameState.RUNNING;
    }

    public Game(board board, tetraminoFactory tetraminoFactory, GameState gameState) {
        this.board = board;
        this.tetraminoFactory = tetraminoFactory;
        this.gameState = gameState;
    }

    public board getBoard() {
        return board;
    }

    public void setBoard(board board) {
        this.board = board;
    }

    public tetraminoFactory getTetraminoFactory() {
        return tetraminoFactory;
    }

    public void setTetraminoFactory(tetraminoFactory tetraminoFactory) {
        this.tetraminoFactory = tetraminoFactory;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
