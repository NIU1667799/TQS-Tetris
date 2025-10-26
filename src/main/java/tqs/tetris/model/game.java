package tqs.tetris.model;

public class game {
    private board board;
    private tetraminoFactory tetraminoFactory;
    private gameState gameState;

    public game() {
        this.board = new board();
        this.tetraminoFactory = new tetraminoFactory();
        this.gameState = new gameState();
    }

    public game(board board, tetraminoFactory tetraminoFactory, gameState gameState) {
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

    public gameState getGameState() {
        return gameState;
    }

    public void setGameState(gameState gameState) {
        this.gameState = gameState;
    }
}
