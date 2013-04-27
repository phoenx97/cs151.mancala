
import java.awt.*;
import java.awt.geom.*;

/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * Rectangle shaped player mancala
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class SquareMancala implements Pit 
{
    private int width;
    private int height;
    private int stones;
    
    /**
     * Constructor
     * @param  width width of the pit
     */
    public SquareMancala(int width) 
    { 
        this.width = width; 
        height = width * 2;
    }

    /**
     * Draws the pit
     * @param c component
     * @param g graphics context
     * @param x x-offset
     * @param y y-offset
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D.Double pit = new Rectangle2D.Double(x, y, width, height);
        
        g2.draw(pit);
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawString(String.valueOf(stones), width / 2, height);
    }
    
    /**
     * Gets the width of the pit
     * @return width
     */
    @Override
    public int getIconWidth() { return width; }

    /**
     * Gets the height of the pit
     * @return height
     */
    @Override
    public int getIconHeight() { return height; }

    /**
     * Sets the number of stones to be in the pit
     * @param numStones number of stones
     */
    @Override
    public void setStones(int numStones) { stones = numStones; }
}
