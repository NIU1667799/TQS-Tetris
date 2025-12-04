package tqs.tetris.model;

public class MockGameState {
	private final GameState value;

	public MockGameState(String value) {
		this.value = GameState.valueOf(value); 
	}

    public GameState getValue() {
        return value;
	}
}
