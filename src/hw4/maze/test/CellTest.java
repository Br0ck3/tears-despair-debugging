package hw4.maze.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import hw4.maze.Cell;
import hw4.maze.CellComponents;

class CellTest {
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
	void testGetters() {
		// Create a cell with known components
		Cell cell = new Cell(CellComponents.EXIT, CellComponents.WALL, CellComponents.APERTURE, CellComponents.WALL);
		
		// Test each getter returns the correct value
		assertEquals(CellComponents.EXIT, cell.getLeft());
		assertEquals(CellComponents.WALL, cell.getRight());
		assertEquals(CellComponents.APERTURE, cell.getUp());
		assertEquals(CellComponents.WALL, cell.getDown());
	}
	
	@Test
	void testToString() {
		Cell cell = new Cell(CellComponents.EXIT, CellComponents.APERTURE, CellComponents.WALL, CellComponents.APERTURE);
		assertTrue(cell.toString().contains("Cell [left="));
		assertTrue(cell.toString().contains("right="));
		assertTrue(cell.toString().contains("up="));
		assertTrue(cell.toString().contains("down="));
	}
	
	@ParameterizedTest
	@MethodSource("providingGetterReturns")
	void testGetters(CellComponents left, CellComponents right, CellComponents up, CellComponents down) {
		// Create cell with provided components
		Cell cell = new Cell(left, right, up, down);
		
		// Test each getter matches its respective parameter
		assertEquals(left, cell.getLeft());
		assertEquals(right, cell.getRight());
		assertEquals(up, cell.getUp());
		assertEquals(down, cell.getDown());
	}
	
	private static Stream<Arguments> providingGetterReturns() {
		return Stream.of(
			// Test case 1: All APERTURE
			Arguments.of(CellComponents.APERTURE, CellComponents.APERTURE, CellComponents.APERTURE, CellComponents.APERTURE),
			// Test case 2: All WALL
			Arguments.of(CellComponents.WALL, CellComponents.WALL, CellComponents.WALL, CellComponents.WALL),
			// Test case 3: Mixed components
			Arguments.of(CellComponents.EXIT, CellComponents.WALL, CellComponents.APERTURE, CellComponents.WALL),
			// Test case 4: Different mixed components
			Arguments.of(CellComponents.WALL, CellComponents.EXIT, CellComponents.WALL, CellComponents.APERTURE)
		);
	}
	

	@Test
	void testSetUpWithValidCellComponent() {
		Cell cell = new Cell(CellComponents.WALL, 
				CellComponents.EXIT, 
				CellComponents.APERTURE, 
				CellComponents.WALL);
		cell.setUp(CellComponents.WALL);
		assertEquals(CellComponents.WALL, cell.getUp());
	}
	
	@Test
	void testSetUpWithNullCellComponent() {
		Cell cell = new Cell(CellComponents.WALL, 
				CellComponents.EXIT, 
				CellComponents.APERTURE, 
				CellComponents.WALL);
		cell.setUp(null);
		assertEquals(CellComponents.WALL, cell.getUp());
	}
	
	@Test
	void testSetDownWithValidCellComponent() {
		Cell cell = new Cell(CellComponents.WALL, 
				CellComponents.EXIT, 
				CellComponents.APERTURE, 
				CellComponents.WALL);
		cell.setDown(CellComponents.APERTURE);
		assertEquals(CellComponents.APERTURE, cell.getDown());
	}
	
	@Test
	void testSetDownWithNullCellComponent() {
		Cell cell = new Cell(CellComponents.WALL, 
				CellComponents.EXIT, 
				CellComponents.WALL, 
				CellComponents.APERTURE);
		cell.setDown(null);
		assertEquals(CellComponents.WALL, cell.getDown());
	}
	
	@Test
	void testSetLeftWithValidCellComponent() {
		Cell cell = new Cell(CellComponents.WALL, 
				CellComponents.EXIT, 
				CellComponents.APERTURE, 
				CellComponents.WALL);
		cell.setLeft(CellComponents.APERTURE);
		assertEquals(CellComponents.APERTURE, cell.getLeft());
	}
	
	@Test
	void testSetLeftWithNullCellComponent() {
		Cell cell = new Cell(CellComponents.APERTURE, 
				CellComponents.EXIT, 
				CellComponents.APERTURE, 
				CellComponents.WALL);
		cell.setLeft(null);
		assertEquals(CellComponents.WALL, cell.getLeft());
	}
	
	@Test
	void testSetRightWithValidCellComponent() {
		Cell cell = new Cell(CellComponents.WALL, 
				CellComponents.WALL, 
				CellComponents.APERTURE, 
				CellComponents.WALL);
		cell.setRight(CellComponents.EXIT);
		assertEquals(CellComponents.EXIT, cell.getRight());
	}
	
	@Test
	void testSetRightWithNullCellComponent() {
		Cell cell = new Cell(CellComponents.APERTURE, 
				CellComponents.EXIT, 
				CellComponents.APERTURE, 
				CellComponents.WALL);
		cell.setRight(null);
		assertEquals(CellComponents.WALL, cell.getRight());
	}
	
	
}
