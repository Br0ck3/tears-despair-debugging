package hw4.maze;

import java.util.ArrayList;

/**
 * Represents a row of cells in the maze grid.
 */
public class Row {
    private ArrayList<Cell> cells;

    /**
     * Creates a new row with the specified cells.
     * @param cells The list of cells in this row
     */
    public Row(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    /**
     * Gets the list of cells in this row.
     * @return The list of cells
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }

    /**
     * Sets the list of cells in this row.
     * @param cells The new list of cells
     */
    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return "Row [cells=" + cells + "]";
    }
} 