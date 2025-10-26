package tqs.tetris.model;

public enum GameState {
    RUNNING,
    PAUSED,
    GAME_OVER;

    public boolean isPlayable() {
        return this == RUNNING;
    }

    public boolean isFrozen() {
        return this == PAUSED;
    }

    public boolean isGameOver() {
        return this == GAME_OVER;
    }
}