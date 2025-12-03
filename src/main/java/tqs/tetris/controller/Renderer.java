package tqs.tetris.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import tqs.tetris.model.Board;
import tqs.tetris.model.Game;
import tqs.tetris.model.Tetromino;

/* Class designed to render visual components of our Tetris game */

public class Renderer extends JPanel {
    private Game game;
    private final int cellSize = 30; // pixel size of each block

    public Renderer(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(
                game.getBoard().getWidth() * cellSize,
                game.getBoard().getHeight() * cellSize
        ));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Board board = game.getBoard();
        int width = board.getWidth();
        int height = board.getHeight();

        // draw board cells
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!board.isEmpty(x, y)) {
                    drawCell(g, x, y, Color.GRAY);
                } else {
                    drawCell(g, x, y, Color.DARK_GRAY);
                }
            }
        }

        // draw current tetromino
        Tetromino current = game.getCurrent();
        if (current != null) {
            Color pieceColor = getTetrisColor(current.getColor());
            for (int[] cell : current.getCells()) {
                int cx = cell[0];
                int cy = cell[1];
                if (cy >= 0 && cy < height && cx >= 0 && cx < width) {
                    drawCell(g, cx, cy, pieceColor);
                }
            }
        }

        // Draw game over overlay
        if (game.getGameState().isGameOver()) {
            drawGameOver(g);
        }
    }

    private void drawGameOver(Graphics g) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, panelWidth, panelHeight);

        // Game Over text
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        String gameOverText = "GAME OVER";
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(gameOverText);
        int x = (panelWidth - textWidth) / 2;
        int y = panelHeight / 2 - 20;
        g.drawString(gameOverText, x, y);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        String restartText = "Restart to play again!";
        fm = g.getFontMetrics();
        textWidth = fm.stringWidth(restartText);
        x = (panelWidth - textWidth) / 2;
        y = panelHeight / 2 + 30;
        g.drawString(restartText, x, y);
    }

    private Color getTetrisColor(int c) {
        switch (c) {
            case 0: return Color.CYAN;  
            case 1: return Color.YELLOW;
            case 2: return Color.GREEN;
            case 3: return Color.RED;
            case 4: return Color.BLUE;
            case 5: return Color.ORANGE;
            default: return Color.GRAY;
        }
    }

    private void drawCell(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x * cellSize, (game.getBoard().getHeight() - 1 - y) * cellSize, cellSize, cellSize);
        g.setColor(Color.BLACK);
        g.drawRect(x * cellSize, (game.getBoard().getHeight() - 1 - y) * cellSize, cellSize, cellSize);
    }
}