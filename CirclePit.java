
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class CirclePit implements Pit
{
    protected int width;
    protected int stones;
    protected ArrayList<Shape> shapes;
    
    public CirclePit(int width)
    {
        this.width = width;
        shapes = new ArrayList<Shape>();
    }
    
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double pit = new Ellipse2D.Double(x, y, width, width);
        
        g2.draw(pit);
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawString(String.valueOf(stones), width / 2, width);
    }
    
    @Override
    public int getIconWidth() { return width; }

    @Override
    public int getIconHeight() { return width; }

    @Override
    public void setStones(int numStones) { stones = numStones; }
}
