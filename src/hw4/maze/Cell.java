package hw4.maze;

/**
 * Represents a single cell in the maze grid with four components (left, right, up, down).
 */
public class Cell {
    private CellComponents up;
    private CellComponents down;
    private CellComponents left;
    private CellComponents right;

    /**
     * Creates a new cell with the specified components.
     * @param left The left component of the cell
     * @param right The right component of the cell
     * @param up The up component of the cell
     * @param down The down component of the cell
     */
    public Cell(CellComponents left, CellComponents right, CellComponents up, CellComponents down) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }

    // Creates a new cell with all sides as WALLs
    public Cell() {
        this.up = CellComponents.WALL;
        this.down = CellComponents.WALL;
        this.left = CellComponents.WALL;
        this.right = CellComponents.WALL;
    }

    // Checks if this cell has at least one APERTURE
    public boolean hasAperture() {
        return up == CellComponents.APERTURE || 
               down == CellComponents.APERTURE || 
               left == CellComponents.APERTURE || 
               right == CellComponents.APERTURE;
    }

    /**
     * Gets the left component of the cell.
     * @return The left component
     */
    public CellComponents getLeft() {
        return left;
    }

    /**
     * Gets the right component of the cell.
     * @return The right component
     */
    public CellComponents getRight() {
        return right;
    }

    /**
     * Gets the up component of the cell.
     * @return The up component
     */
    public CellComponents getUp() {
        return up;
    }

    /**
     * Gets the down component of the cell.
     * @return The down component
     */
    public CellComponents getDown() {
        return down;
    }

    /**
     * Sets the left component of the cell.
     * @param left The new left component
     */
    public void setLeft(CellComponents left) {
        this.left = left != null ? left : CellComponents.WALL;
    }

    /**
     * Sets the right component of the cell.
     * @param right The new right component
     */
    public void setRight(CellComponents right) {
        this.right = right != null ? right : CellComponents.WALL;
    }

    /**
     * Sets the up component of the cell.
     * @param up The new up component
     */
    public void setUp(CellComponents up) {
        this.up = up != null ? up : CellComponents.WALL;
    }

    /**
     * Sets the down component of the cell.
     * @param down The new down component
     */
    public void setDown(CellComponents down) {
        this.down = down != null ? down : CellComponents.WALL;
    }

    @Override
    public String toString() {
        return "Cell [left=" + left + ", right=" + right + ", up=" + up + ", down=" + down + "]";
    }
} 