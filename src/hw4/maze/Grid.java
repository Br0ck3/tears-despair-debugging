package hw4.maze;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the maze grid containing multiple rows of cells.
 */
public class Grid {
    private ArrayList<Row> rows;
    private Random random;

    /**
     * Creates a new grid with the specified rows.
     * @param rows The list of rows in this grid
     */
    public Grid(ArrayList<Row> rows) {
        this.rows = rows;
    }

    /**
     * Creates a new random grid with specified size.
     * @param size The size of the grid (must be between 3 and 7)
     */
    public Grid(int size) {
        if (size < 3 || size > 7) {
            throw new IllegalArgumentException("Grid size must be between 3 and 7");
        }
        random = new Random();
        rows = new ArrayList<>();
        
        // Initialize all cells
        for (int row = 0; row < size; row++) {
            ArrayList<Cell> cells = new ArrayList<>();
            for (int col = 0; col < size; col++) {
                cells.add(new Cell());
            }
            rows.add(new Row(cells));
        }

        // Set random apertures for each cell
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Cell cell = getCell(row, col);
                // Ensure at least one aperture
                Direction randomDir = Direction.values()[random.nextInt(4)];
                switch (randomDir) {
                    case UP:
                        cell.setUp(CellComponents.APERTURE);
                        break;
                    case DOWN:
                        cell.setDown(CellComponents.APERTURE);
                        break;
                    case LEFT:
                        cell.setLeft(CellComponents.APERTURE);
                        break;
                    case RIGHT:
                        cell.setRight(CellComponents.APERTURE);
                        break;
                }
                
                // Add some additional random apertures
                for (Direction dir : Direction.values()) {
                    if (random.nextBoolean()) {
                        switch (dir) {
                            case UP:
                                cell.setUp(CellComponents.APERTURE);
                                break;
                            case DOWN:
                                cell.setDown(CellComponents.APERTURE);
                                break;
                            case LEFT:
                                cell.setLeft(CellComponents.APERTURE);
                                break;
                            case RIGHT:
                                cell.setRight(CellComponents.APERTURE);
                                break;
                        }
                    }
                }
            }
        }

        // Set random exit on leftmost column
        int exitRow = random.nextInt(size);
        getCell(exitRow, 0).setLeft(CellComponents.EXIT);
    }

    // Creates a new random grid with size between 3x3 and 7x7
    public Grid() {
        random = new Random();
        // Randomly set grid size between 3 and 7
        int size = random.nextInt(5) + 3;
        rows = new ArrayList<>();
        
        // Initialize all cells
        for (int row = 0; row < size; row++) {
            ArrayList<Cell> cells = new ArrayList<>();
            for (int col = 0; col < size; col++) {
                cells.add(new Cell());
            }
            rows.add(new Row(cells));
        }

        // Set random apertures for each cell
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Cell cell = getCell(row, col);
                // Ensure at least one aperture
                Direction randomDir = Direction.values()[random.nextInt(4)];
                switch (randomDir) {
                    case UP:
                        cell.setUp(CellComponents.APERTURE);
                        break;
                    case DOWN:
                        cell.setDown(CellComponents.APERTURE);
                        break;
                    case LEFT:
                        cell.setLeft(CellComponents.APERTURE);
                        break;
                    case RIGHT:
                        cell.setRight(CellComponents.APERTURE);
                        break;
                }
                
                // Add some additional random apertures
                for (Direction dir : Direction.values()) {
                    if (random.nextBoolean()) {
                        switch (dir) {
                            case UP:
                                cell.setUp(CellComponents.APERTURE);
                                break;
                            case DOWN:
                                cell.setDown(CellComponents.APERTURE);
                                break;
                            case LEFT:
                                cell.setLeft(CellComponents.APERTURE);
                                break;
                            case RIGHT:
                                cell.setRight(CellComponents.APERTURE);
                                break;
                        }
                    }
                }
            }
        }

        // Set random exit on leftmost column
        int exitRow = random.nextInt(size);
        getCell(exitRow, 0).setLeft(CellComponents.EXIT);
    }

    // Returns the cell at specified coordinates
    public Cell getCell(int row, int col) {
        if (row < 0 || row >= getSize() || col < 0 || col >= getSize()) {
            throw new IllegalArgumentException("Invalid cell coordinates: (" + row + ", " + col + ")");
        }
        return rows.get(row).getCells().get(col);
    }

    // Prints the grid with agent position marked as 'A' and exit as 'E'
    public void printGrid(int agentRow, int agentCol) {
        if (agentRow < 0 || agentRow >= getSize() || agentCol < 0 || agentCol >= getSize()) {
            throw new IllegalArgumentException("Invalid agent coordinates: (" + agentRow + ", " + agentCol + ")");
        }

        for (int row = 0; row < getSize(); row++) {
            for (int col = 0; col < getSize(); col++) {
                if (row == agentRow && col == agentCol) {
                    System.out.print("A ");
                } else if (col == 0 && getCell(row, col).getLeft() == CellComponents.EXIT) {
                    System.out.print("E ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.println();
        }
    }

    // Returns the size of the grid (number of rows/columns)
    public int getSize() {
        return rows.size();
    }

    /**
     * Gets the list of rows in this grid.
     * @return The list of rows
     */
    public ArrayList<Row> getRows() {
        return rows;
    }

    /**
     * Sets the list of rows in this grid.
     * @param rows The new list of rows
     */
    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "Grid [rows=" + rows + "]";
    }

    /**
     * Verifies that the grid meets all requirements:
     * - Each cell has at least one APERTURE
     * - Exactly one EXIT exists on the left side
     * @return true if the grid is valid, false otherwise
     */
    public boolean isValid() {
        int exitCount = 0;
        boolean allCellsHaveAperture = true;
        
        for (int row = 0; row < getSize(); row++) {
            for (int col = 0; col < getSize(); col++) {
                Cell cell = getCell(row, col);
                
                // Check for EXIT on left side
                if (col == 0 && cell.getLeft() == CellComponents.EXIT) {
                    exitCount++;
                }
                
                // Check for at least one APERTURE
                if (!cell.hasAperture()) {
                    allCellsHaveAperture = false;
                }
            }
        }
        
        return exitCount == 1 && allCellsHaveAperture;
    }
} 