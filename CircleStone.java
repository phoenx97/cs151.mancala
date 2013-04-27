
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * Circle shaped stone
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class CircleStone implements Stone {
    private int stones;
    private int width;
    private ArrayList<Shape> shapes;
    
    public CircleStone(int width)
    {
        this.width = width;
    }
    
    @Override
    public void setStones(int numStones)
    {
        stones = numStones;
    }

    @Override
    public Rectangle getBounds()
    {
        int height = (stones / 4) * width;
        return new Rectangle(width, height);
    }

    @Override
    public void draw(Graphics2D g2)
    {
        shapes = new ArrayList<Shape>();
        
        for (int i = 0; i < stones; i++)
        {
            Ellipse2D.Double stone = new Ellipse2D.Double(0, i * width + 5, width, width); // something not drawing correctly
            shapes.add(stone);
        }
        
        for (Shape s : shapes)
        {
            g2.setPaint(Color.BLACK);
            g2.setStroke(new BasicStroke(3.0f));
            g2.draw(s);
            g2.setPaint(Color.BLUE);
            g2.fill(s);
        }
    }
}
