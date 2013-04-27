
import java.awt.*;

/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * Interface for the stones
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public interface Stone
{
    public static final int MAX_COL = 5;
    public static final int MAX_MANCALA_COL = 10;
    
    /**
     * Sets if the stone is part of a player mancala
     * @param mancala 
     */
    void setMancala();
    
    /**
     * Sets the number of stones to draw
     * @param numStones number of stones
     */
    void setStones(int numStones);
    
    /**
     * Returns an integer Rectangle that completely encloses the Shape
     * @return Rectangle enclosing the shape
     */
    Rectangle getBounds();
    
    /**
     * Draws this shape
     * @param g2 the graphics context
     */
    void draw(Graphics2D g2);
}
