/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */

package model;

/**
 * Represents a position with coordinates (x, y).
 */
public class Position {
    final private int myX;
    final private int myY;

    /**
     * Constructs a new Position object with the specified coordinates.
     * @param theX The theX-coordinate.
     * @param theY The theY-coordinate.
     */
    public Position(final int theX, final int theY) {
        this.myX = theX;
        this.myY = theY;
    }
}
