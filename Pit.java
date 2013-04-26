
import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.Icon;

/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class Pit implements Icon
{
    protected int width;
    
    public Pit(int width)
    {
        this.width = width;
    }
    
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double pit = new Ellipse2D.Double(x, y, width, width);
        
        g2.draw(pit);
    }

    @Override
    public int getIconWidth()
    {
        return width;
    }

    @Override
    public int getIconHeight()
    {
        return width;
    }
}
