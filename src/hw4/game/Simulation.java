package hw4.game;

import java.util.Scanner;

import hw4.maze.Direction;
import hw4.maze.Grid;
import hw4.player.Agent;

public class Simulation {
    public static void main(String[] args) {
        Grid grid = new Grid();
        // Start agent in bottom-right corner
        Agent agent = new Agent(grid, grid.getSize() - 1, grid.getSize() - 1);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Escape Game!");
        System.out.println("Initial Grid:");
        grid.printGrid(agent.getRow(), agent.getCol());

        while (!agent.hasEscaped()) {
            System.out.println("\nEnter move (UP, DOWN, LEFT, RIGHT):");
            String input = scanner.nextLine().toUpperCase();

            try {
                Direction direction = Direction.valueOf(input);
                boolean success = agent.move(direction);
                
                if (success) {
                    grid.printGrid(agent.getRow(), agent.getCol());
                    if (agent.hasEscaped()) {
                        System.out.println("\n🎉 Congratulations! You've escaped through the EXIT!");
                    }
                } else {
                    System.out.println("Invalid move! You can't move " + direction + " from here.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter UP, DOWN, LEFT, or RIGHT.");
            }
        }

        scanner.close();
    }
} 