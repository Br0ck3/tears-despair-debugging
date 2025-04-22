package edu.mizzou.project04;

public class Cell {
    private Component up;
    private Component down;
    private Component left;
    private Component right;

    public Cell() {
        // Initialize all components as WALL by default
        this.up = Component.WALL;
        this.down = Component.WALL;
        this.left = Component.WALL;
        this.right = Component.WALL;
    }

    public boolean hasAperture() {
        return up == Component.APERTURE || 
               down == Component.APERTURE || 
               left == Component.APERTURE || 
               right == Component.APERTURE;
    }

    public Component getComponent(Direction dir) {
        switch (dir) {
            case UP:
                return up;
            case DOWN:
                return down;
            case LEFT:
                return left;
            case RIGHT:
                return right;
            default:
                throw new IllegalArgumentException("Invalid direction: " + dir);
        }
    }

    public void setComponent(Direction dir, Component comp) {
        switch (dir) {
            case UP:
                up = comp;
                break;
            case DOWN:
                down = comp;
                break;
            case LEFT:
                left = comp;
                break;
            case RIGHT:
                right = comp;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + dir);
        }
    }
} 