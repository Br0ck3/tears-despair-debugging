package hw4.player;

import hw4.maze.Cell;
import hw4.maze.CellComponents;
import hw4.maze.Direction;
import hw4.maze.Grid;

public class Agent {
    private int row;
    private int col;
    private Grid grid;
    private boolean hasEscaped;

    public Agent(Grid grid, int startRow, int startCol) {
        if (startRow < 0 || startRow >= grid.getSize() || 
            startCol < 0 || startCol >= grid.getSize()) {
            throw new IllegalArgumentException("Invalid starting position");
        }
        this.grid = grid;
        this.row = startRow;
        this.col = startCol;
        this.hasEscaped = false;
    }

    public boolean move(Direction direction) {
        if (hasEscaped) {
            return false;
        }

        Cell current = grid.getCell(row, col);
        CellComponents component = null;

        switch (direction) {
            case UP:
                component = current.getUp();
                if (component == CellComponents.APERTURE && row > 0) {
                    row--;
                    return true;
                }
                break;
            case DOWN:
                component = current.getDown();
                if (component == CellComponents.APERTURE && row < grid.getSize() - 1) {
                    row++;
                    return true;
                }
                break;
            case LEFT:
                component = current.getLeft();
                if (component == CellComponents.APERTURE && col > 0) {
                    col--;
                    return true;
                } else if (component == CellComponents.EXIT && col == 0) {
                    hasEscaped = true;
                    return true;
                }
                break;
            case RIGHT:
                component = current.getRight();
                if (component == CellComponents.APERTURE && col < grid.getSize() - 1) {
                    col++;
                    return true;
                }
                break;
        }
        return false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Cell getCurrentCell() {
        return grid.getCell(row, col);
    }

    public boolean hasEscaped() {
        return hasEscaped;
    }

    @Override
    public String toString() {
        return "Agent [row=" + row + ", col=" + col + "]";
    }
} 