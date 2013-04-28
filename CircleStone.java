
import java.awt.*;
import java.awt.geom.*;

/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * Circle shaped stone
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class CircleStone implements Stone {
    private int stones;
    private int width;
    private int max_col;
    
    public CircleStone(int width) { 
        this.width = width; 
        max_col = MAX_COL; 
    }
    
    @Override
    public void setMancala() { max_col = MAX_MANCALA_COL; }
    
    @Override
    public void setStones(int numStones) { stones = numStones; }

    @Override
    public Rectangle getBounds() { return new Rectangle(((stones / max_col) * width), (max_col * width)); }

    @Override
    public void draw(Graphics2D g2)
    {
        int col = 0;
        int row = 0;
        
        for (int i = 0; i < stones; i++)
        {
            if (row >= max_col)
            {
                col++;
                row = 0;
            }
            Ellipse2D.Double stone = new Ellipse2D.Double(col * width, row * width, width, width);

            Color[] colors = {new Color(150, 190, 210), new Color(0, 70, 150)};
            float[] dist = {0.0f, 0.8f};
            RadialGradientPaint p = new RadialGradientPaint(new Point2D.Float((width / 3) + (col * width), (width / 3) + (row * width)), width / 2, dist, colors);
            g2.setPaint(p);
            g2.fill(stone);
            g2.setPaint(Color.BLACK);
            g2.setStroke(new BasicStroke(1.0f));
            g2.draw(stone);
            
            row++;
        }
    }
}
