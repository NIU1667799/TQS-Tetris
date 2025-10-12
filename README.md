# TQS-Tetris

A simple tetris game for a class project.

# Tetris Class Distribution

## Core Game Classes
- **Game**
  - Main loop, orchestrates updates and rendering
  - Holds references to `Board`, `Tetromino`, `Score`, etc.
- **Board**
  - 2D grid representing the playfield
  - Handles collision detection, line clearing, and storing placed blocks
- **Tetromino**
  - Represents a falling piece
  - Knows its shape, rotation states, and current position
- **TetrominoFactory**
  - Generates random tetrominoes (I, O, T, S, Z, J, L)
  - Implements “bag” system for fairness
- **Block**
  - Smallest unit (a single square)
  - Stores color and position relative to the board
- **Score**
  - Tracks points, lines cleared, and level progression
- **GameState** (enum/class)
  - Defines states like `RUNNING`, `PAUSED`, `GAME_OVER`

## Rendering & UI
- **GamePanel** (Swing/JavaFX)
  - Responsible for drawing the board and pieces
- **Renderer** (optional abstraction)
  - Decouples drawing logic from game logic
- **UIManager**
  - Handles menus, score display, next-piece preview, etc.

## Input Handling
- **InputHandler**
  - Listens for keyboard events (left, right, rotate, drop)
  - Updates the `Game` or `Tetromino` accordingly

## Utility / Extras
- **Timer / GameLoop**
  - Controls piece falling speed and level progression

# Proposed file structure
src/
 ├── model/
 │    ├── Game.java
 │    ├── Board.java
 │    ├── Tetromino.java
 │    ├── TetrominoFactory.java
 │    ├── Block.java
 │    ├── Score.java
 │    ├── GameState.java   (enum)
 │
 ├── view/
 │    ├── GamePanel.java
 │    ├── Renderer.java
 │    ├── UIManager.java
 │    └── Main.java
 │
 ├── controller/
 │    ├── GameController.java
 │    ├── InputHandler.java


# Breakdown by Layer
- **Model (Game Logic)**
    - Game.java → Core game loop, orchestrates updates.
    - Board.java → Grid, collision detection, line clearing.
    - Tetromino.java → Represents a piece (rotation, movement).
    - TetrominoFactory.java → Creates random tetrominoes.
    - Block.java → Smallest unit (color + position).
    - Score.java → Tracks points, lines, levels.
    - GameState.java → Enum for RUNNING, PAUSED, GAME_OVER.

- **View (UI/Rendering)**
    - GamePanel.java → Draws the board and pieces (Swing/JavaFX).
    - Renderer.java → Optional helper to separate drawing logic.
    - UIManager.java → Handles menus, score display, next-piece preview.

- **Controller (Input & Coordination)**
    - GameController.java → Mediates between Model and View. Updates model state. Tells view when to repaint.
    - InputHandler.java → Captures keyboard input and passes commands to GameController.

- **Entry Point**
    - Main.java → Starts the application, initializes MVC components.

 ## Posible MVC Flow
    - Controller (InputHandler) → detects key press (e.g., rotate).

    - Controller (GameController) → updates Model (Tetromino inside Board).

    - Model → changes state (piece rotates, moves, or locks).

    - View (GamePanel) → repaints based on updated model.