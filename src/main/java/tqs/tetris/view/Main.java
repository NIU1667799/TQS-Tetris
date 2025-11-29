package tqs.tetris.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import tqs.tetris.controller.Renderer;
import tqs.tetris.model.Board;
import tqs.tetris.model.Game;
import tqs.tetris.model.GameState;
import tqs.tetris.model.TetrominoFactory;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(10, 20);
        TetrominoFactory factory = new TetrominoFactory();
        GameState state = GameState.RUNNING;
        Game game = new Game(board, factory, state);
        Renderer renderer = new Renderer(game);

        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(renderer);
        frame.pack();
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocus();

        // Game loop
        new Timer(500, e -> {
            if (game.getGameState().isPlayable()) {
                game.tick();
                renderer.repaint();
            }
        }).start();

        // Keyboard input
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        game.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.moveRight();
                        break;
                    case KeyEvent.VK_DOWN:
                        game.tick(); // faster drop
                        break;
                    case KeyEvent.VK_UP:
                        game.rotateCurrent();
                        break;
                }
                renderer.repaint();
            }
        });
    }
}
