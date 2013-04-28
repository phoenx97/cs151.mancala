
import java.awt.*;
import java.awt.geom.*;

/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * Circle shaped pit
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class CirclePit implements Pit
{
    private int width;
    private int stones;
    private Stone stoneShape;
    
    /**
     * Constructor
     * @param  width width of the pit
     */
    public CirclePit(Stone stoneShape, int width) 
    {
        this.width = width; 
        this.stoneShape = stoneShape;
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
        Ellipse2D.Double pit = new Ellipse2D.Double(x, y, width, width);
        
        Color[] colors = {new Color(190, 180, 135), new Color(240, 225, 180)};
        float[] dist = {0.0f, 0.9f};
        RadialGradientPaint p = new RadialGradientPaint(new Point2D.Float(width / 2, width / 2), width / 2, dist, colors);
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setPaint(p);
        g2.fill(pit);
        g2.setPaint(Color.GRAY);
        g2.setStroke(new BasicStroke(2.0f));
        g2.draw(pit);
        
        g2.setColor(Color.GRAY);        
        g2.drawString(String.valueOf(stones), width / 2, width);
        
        g2.translate(width / Stone.MAX_COL, width / Stone.MAX_COL);
        stoneShape.draw(g2);
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
    public int getIconHeight() { return width; }

    /**
     * Sets the number of stones in the pit
     * @param numStones number of stones
     */
    @Override
    public void setStones(int numStones) 
    {
        stones = numStones;
        stoneShape.setStones(numStones);
    }
}
