package hw4.maze;

// Represents possible components that can be on a cell's side
public enum CellComponents {
    // Solid wall that blocks movement
    WALL,
    // Opening that allows movement
    APERTURE,
    // Special opening that allows escape from the grid
    EXIT
} 