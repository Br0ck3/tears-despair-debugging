package hw4.player;

import hw4.maze.Row;
import hw4.maze.Cell;

/**
 * Represents a player in the maze game.
 */
public class Player {
    private Row currentRow;
    private Cell currentCell;

    /**
     * Creates a new player at the specified position.
     * @param row The row containing the player's current cell
     * @param cell The player's current cell
     */
    public Player(Row row, Cell cell) {
        this.currentRow = row;
        this.currentCell = cell;
    }

    /**
     * Gets the player's current row.
     * @return The current row
     */
    public Row getCurrentRow() {
        return currentRow;
    }

    /**
     * Sets the player's current row.
     * @param row The new row
     */
    public void setCurrentRow(Row row) {
        this.currentRow = row;
    }

    /**
     * Gets the player's current cell.
     * @return The current cell
     */
    public Cell getCurrentCell() {
        return currentCell;
    }

    /**
     * Sets the player's current cell.
     * @param cell The new cell
     */
    public void setCurrentCell(Cell cell) {
        this.currentCell = cell;
    }

    @Override
    public String toString() {
        return "Player [currentCell=" + currentCell + ", currentRow=" + currentRow + "]";
    }
} 