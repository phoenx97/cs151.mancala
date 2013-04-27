
import java.awt.*;
import java.awt.geom.*;

/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class SquarePlayerPit extends CirclePit 
{
    public SquarePlayerPit(int width) { super(width); }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D.Double pit = new Rectangle2D.Double(x, y, width, width * 2);
        
        g2.draw(pit);
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawString(String.valueOf(stones), width / 2, width);
    }
    
    @Override
    public int getIconHeight() { return width * 2; }
}
