package hw4.game;

import hw4.maze.Grid;
import hw4.player.Player;
import hw4.player.Movement;

/**
 * Manages the maze game, including grid creation and player movement.
 */
public class Game {
    private Grid grid;

    /**
     * Creates a new game with the specified grid.
     * @param grid The grid to use for this game
     */
    public Game(Grid grid) {
        this.grid = grid;
    }

    /**
     * Creates a new game with a random grid of the specified size.
     * @param size The size of the grid (must be between 3 and 7)
     */
    public Game(int size) {
        this.grid = createRandomGrid(size);
    }

    /**
     * Creates a random grid of the specified size.
     * @param size The size of the grid (must be between 3 and 7)
     * @return The created grid, or null if size is invalid
     */
    public Grid createRandomGrid(int size) {
        if (size < 3 || size > 7) {
            return null;
        }
        
        // Create grid and verify it meets requirements
        Grid grid = new Grid(size);
        if (!grid.isValid()) {
            // If grid is invalid, try again (up to 10 times)
            for (int i = 0; i < 10; i++) {
                grid = new Grid(size);
                if (grid.isValid()) {
                    break;
                }
            }
        }
        
        return grid;
    }

    /**
     * Gets the current grid.
     * @return The current grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Sets the current grid.
     * @param grid The new grid
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     * Attempts to move the player in the specified direction.
     * @param movement The direction to move
     * @param player The player to move
     * @return true if the movement was successful, false otherwise
     */
    public boolean play(Movement movement, Player player) {
        // basic placeholder — update with correct logic later
        return true;
    }

    @Override
    public String toString() {
        return "Game [grid=" + grid + "]";
    }
} 