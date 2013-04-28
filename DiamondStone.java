
import java.awt.*;
import java.awt.geom.*;

/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * Circle shaped stone
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class DiamondStone implements Stone {
    private int stones;
    private int width;
    private int max_col;
    
    public DiamondStone(int width) { 
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
            Polygon stone = new Polygon();
            stone.addPoint((col * width), (row * width) + (width / 2));
            stone.addPoint((col * width) + (width / 2), (row * width) + width);
            stone.addPoint((col * width) + width, (row * width) + (width / 2));
            stone.addPoint((col * width) + (width / 2), (row * width));
            
            GradientPaint p = new GradientPaint(0, 0, new Color(255, 0, 0), 0, width, new Color(130, 0, 0)); // fix this later
            
            g2.setPaint(p);
            g2.fill(stone);
            g2.setPaint(Color.BLACK);
            g2.setStroke(new BasicStroke(0.5f));
            g2.draw(stone);
            
            row++;
        }
    }
}
