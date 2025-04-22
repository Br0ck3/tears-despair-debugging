package edu.mizzou.project04;

import java.util.Random;

public class Grid {
    private Cell[][] cells;
    private int size;
    private Random random;

    public Grid() {
        random = new Random();
        // Randomly set grid size between 3 and 7
        size = random.nextInt(5) + 3;
        cells = new Cell[size][size];
        
        // Initialize all cells
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                cells[row][col] = new Cell();
            }
        }

        // Set random apertures for each cell
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Cell cell = cells[row][col];
                // Ensure at least one aperture
                Direction randomDir = Direction.values()[random.nextInt(4)];
                cell.setComponent(randomDir, Component.APERTURE);
                
                // Add some additional random apertures
                for (Direction dir : Direction.values()) {
                    if (random.nextBoolean()) {
                        cell.setComponent(dir, Component.APERTURE);
                    }
                }
            }
        }

        // Set random exit on leftmost column
        int exitRow = random.nextInt(size);
        cells[exitRow][0].setComponent(Direction.LEFT, Component.EXIT);
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IllegalArgumentException("Invalid cell coordinates: (" + row + ", " + col + ")");
        }
        return cells[row][col];
    }

    public void printGrid(int agentRow, int agentCol) {
        if (agentRow < 0 || agentRow >= size || agentCol < 0 || agentCol >= size) {
            throw new IllegalArgumentException("Invalid agent coordinates: (" + agentRow + ", " + agentCol + ")");
        }

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (row == agentRow && col == agentCol) {
                    System.out.print("A ");
                } else if (col == 0 && cells[row][col].getComponent(Direction.LEFT) == Component.EXIT) {
                    System.out.print("E ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }
} 