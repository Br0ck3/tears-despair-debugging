package hw4.game.test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import hw4.game.Game;
import hw4.maze.Cell;
import hw4.maze.CellComponents;
import hw4.maze.Grid;
import hw4.maze.Row;
import hw4.player.Movement;
import hw4.player.Player;

class GameTest {
	private static Game game;
	private static Grid grid;
	private static Player player;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		setupGame(); // initialize a grid
		game = new Game(grid);    
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testGetGrid() {
		Grid grid = setupGame(); // initialize a grid
		Game game = new Game(grid);    
		assertEquals(grid, game.getGrid());
	}

	@ParameterizedTest
	@MethodSource("playMovementProvider")
	void testPlay(boolean expected, boolean actual) {
		// Create a simple grid with known layout
		Cell cell00 = new Cell(CellComponents.EXIT, CellComponents.APERTURE, CellComponents.WALL, CellComponents.APERTURE);
		Cell cell01 = new Cell(CellComponents.APERTURE, CellComponents.WALL, CellComponents.WALL, CellComponents.APERTURE);
		Cell cell02 = new Cell(CellComponents.WALL, CellComponents.WALL, CellComponents.WALL, CellComponents.APERTURE);
		
		ArrayList<Cell> cells = new ArrayList<>();
		cells.add(cell00);
		cells.add(cell01);
		cells.add(cell02);
		Row row = new Row(cells);
		
		ArrayList<Row> rows = new ArrayList<>();
		rows.add(row);
		Grid grid = new Grid(rows);
		
		Game game = new Game(grid);
		Player player = new Player(row, cell01); // Start at cell01
		
		// Test movement - should be valid since cell01.left is APERTURE and cell00.right is APERTURE
		boolean result = game.play(Movement.LEFT, player);
		assertEquals(expected, result);
		
		// Add debug prints to help diagnose movement
		System.out.println("Grid layout: " + grid);
		System.out.println("Player position: " + player);
		System.out.println("Movement result: " + result);
		System.out.println("cell01.left: " + cell01.getLeft());
		System.out.println("cell00.right: " + cell00.getRight());
	}

	@Test
	public void testSetGrid() {
		Grid grid = setupGame(); // initialize a grid
		Game game = new Game(grid);    
		game.setGrid(null);
		assertEquals(null, game.getGrid());
	}

	@Test
	void testToString() {
		Game game = new Game(3);
		assertTrue(game.toString().contains("Game [grid="));
	}
	
	@Test
	void testAdjacentCellsSharedCellComponentConsistency() {
		Game game = new Game(3);
		Grid grid = game.createRandomGrid(5);
		assertEquals(true, areGridCellsConsistent(grid));
	}
	
	private boolean areGridCellsConsistent(Grid grid) {
		for(int i=0; i<grid.getRows().size(); i++) {
			// check vertical shared cell components (fixed row)
			ArrayList<Cell> cells = grid.getRows().get(i).getCells();
			for(int j=0; j<cells.size() - 1; j++) {
				System.out.println(cells.get(j).getRight() + " - " + cells.get(j+1).getLeft());
				if(!cells.get(j).getRight().equals(cells.get(j+1).getLeft())) {
					return false;
				}
			}
			System.out.println("Row finished");
			// check horizontal shared cell components (fixed column)
			for(int j=0; j<grid.getRows().size() - 1; j++) {
				System.out.println(grid.getRows().get(j).getCells().get(i).getDown() + " - " + grid.getRows().get(j+1).getCells().get(i).getUp());
				if(!grid.getRows().get(j).getCells().get(i).getDown().equals(grid.getRows().get(j+1).getCells().get(i).getUp())) {
					return false;
				}
				System.out.println("Column finished");
			}
		}
		return true;
	}
	
	@Test
	void testIfThereIsAnExitOnLeft() {
		Game game = new Game(3);
		assertEquals(true, isThereAnExitOnLeftSideGrid(game.createRandomGrid(5)));
	}
	
	@ParameterizedTest
	@MethodSource("invalidGridSizeInputProvider")
	void testInvalidGridSizeInput(Grid expected, Grid actual) {
		assertEquals(expected, actual);
	}
	
	private static Stream<Arguments> invalidGridSizeInputProvider() {
		Game game = new Game(3);
		return Stream.of(Arguments.of(null, game.createRandomGrid(2)),
							Arguments.of(null, game.createRandomGrid(8)));
	}
	
	@Test
	void testInvalidMovement() {
		// Create a cell with all walls
		Cell cell = new Cell(CellComponents.WALL, CellComponents.WALL, CellComponents.WALL, CellComponents.WALL);
		
		// Create a single-cell grid
		ArrayList<Cell> cells = new ArrayList<>(List.of(cell));
		Row row = new Row(cells);
		ArrayList<Row> rows = new ArrayList<>(List.of(row));
		Grid grid = new Grid(rows);
		
		// Create game and player
		Game game = new Game(grid);
		Player player = new Player(row, cell);
		
		// Test all movement directions - should all be blocked by walls
		assertEquals(false, game.play(Movement.UP, player));
		assertEquals(false, game.play(Movement.DOWN, player));
		assertEquals(false, game.play(Movement.LEFT, player));
		assertEquals(false, game.play(Movement.RIGHT, player));
	}
	
	@Test
	void testInvalidNullPlayer() {
		Grid grid = setupGame(); // initialize a grid
		Game game = new Game(grid);    
		assertEquals(false, game.play(Movement.UP, null));		
	}
	
	@Test
	void testIfThereIsOnlyOneExitOnLeft() {
		Game game = new Game(3);
		assertEquals(true, isThereOnlyOneExit(game.createRandomGrid(5)));
	}


	private static Grid setupGame() {
		Cell cell00 = new Cell(CellComponents.EXIT, CellComponents.APERTURE,
				CellComponents.WALL, CellComponents.APERTURE);

		Cell cell01 = new Cell(CellComponents.APERTURE, CellComponents.WALL,
				CellComponents.WALL, CellComponents.APERTURE);

		Cell cell02 = new Cell(CellComponents.WALL, CellComponents.WALL,
				CellComponents.WALL, CellComponents.APERTURE);

		Cell cell10 = new Cell(CellComponents.WALL, CellComponents.WALL,
				CellComponents.APERTURE, CellComponents.APERTURE);

		Cell cell11 = new Cell(CellComponents.WALL, CellComponents.APERTURE,
				CellComponents.APERTURE, CellComponents.APERTURE);

		Cell cell12 = new Cell(CellComponents.APERTURE, CellComponents.WALL,
				CellComponents.APERTURE, CellComponents.APERTURE);

		Cell cell20 = new Cell(CellComponents.WALL, CellComponents.WALL,
				CellComponents.APERTURE, CellComponents.WALL);

		Cell cell21 = new Cell(CellComponents.WALL, CellComponents.WALL,
				CellComponents.APERTURE, CellComponents.WALL);

		Cell cell22 = new Cell(CellComponents.WALL, CellComponents.WALL,
				CellComponents.APERTURE, CellComponents.WALL);


		ArrayList<Cell> cells = new ArrayList<Cell>();
		cells.add(0, cell00);
		cells.add(1, cell01);
		cells.add(2, cell02);
		Row row0 = new Row(cells);

		cells = new ArrayList<Cell>();
		cells.add(0, cell10);
		cells.add(1, cell11);
		cells.add(2, cell12);
		Row row1 = new Row(cells);

		cells = new ArrayList<Cell>();
		cells.add(0, cell20);
		cells.add(1, cell21);
		cells.add(2, cell22);
		Row row2 = new Row(cells);

		ArrayList<Row> rows = new ArrayList<Row>();
		rows.add(0, row0);
		rows.add(1, row1);
		rows.add(2, row2);
		Grid grid = new Grid(rows);
		return grid;
	}

	private static boolean isThereAnExitOnLeftSideGrid(Grid grid) {
		int gridSize = grid.getRows().size();

		for(int j=0; j<gridSize; j++) {
			if(grid.getRows().get(j).getCells().get(0).getLeft() == CellComponents.EXIT) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean isThereOnlyOneExit(Grid grid) {
		int gridSize = grid.getRows().size();

		int exitCount = 0;
		for(int j=0; j<gridSize; j++) {
			if(grid.getRows().get(j).getCells().get(0).getLeft() == CellComponents.EXIT) {
				exitCount++;
			}
		}
		if(exitCount != 1) {
			return false;
		}
		return true;
	}

	private static Stream<Arguments> playMovementProvider() {
		return Stream.of(
			Arguments.of(true, true) // Movement should be valid
		);
	}
}
