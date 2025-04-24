# Project 04: Grid Escape Game

CMP_SC / INFO_TC 3330  
Spring 2025  
Brock Burchett + Eric Hudson

## Overview

A grid-based escape game where an agent must navigate through a randomly generated NxN grid (3x3 to 7x7) to reach and escape through an EXIT on the leftmost side. The agent can only move through APERTUREs and must find a path to freedom!

## Implementation Details

### Core Classes

- `CellComponents` - Enum for cell sides (WALL, APERTURE, EXIT)
- `Direction` - Enum for movement directions (UP, DOWN, LEFT, RIGHT)
- `Cell` - Represents a single cell with components on each side
- `Row` - Holds a row of cells in the grid
- `Grid` - Manages the game grid and ensures valid generation
- `Agent` - Handles movement logic and tracks position
- `Simulation` - Main game loop and user interface

### Key Features

- Random grid generation (3x3 to 7x7)
- Each cell has at least one APERTURE
- Single EXIT placed on leftmost column
- Agent can only move through APERTUREs
- Game ends when agent moves LEFT through EXIT

### How to Run

1. Compile all Java files
2. Run the Simulation class
3. Use UP/DOWN/LEFT/RIGHT commands to move
4. Try to escape through the EXIT!

## Testing

Used JUnit5 for test-driven development. All test cases pass:
- CellTest - Validates cell component management
- RowTest - Tests row functionality
- GridTest - Ensures proper grid generation
- PlayerTest - Verifies agent movement logic

All functionality is demonstrated via JUnit tests and Simulation.java

## Contributors

- Brock Burchett: Grid generation, Cell implementation
- Eric Hudson: Agent movement, game simulation

## Notes

- Built using Java and JUnit5
- Follows object-oriented principles
- Includes error handling and input validation
- Uses proper Java naming conventions

---

More updates soon once we have something built!

