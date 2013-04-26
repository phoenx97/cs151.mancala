
import java.awt.*;
import java.awt.geom.*;

/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class PlayerPit extends Pit
{
    public PlayerPit(int width)
    {
        super(width);
    }
    
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double pit = new Ellipse2D.Double(x, y, width, width * 2);
        
        g2.draw(pit);
    }
    
    public int getIconHeight()
    {
        return width * 2;
    }
}
