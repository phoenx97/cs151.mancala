
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class MancalaBoard extends JFrame implements ChangeListener
{
    private GameData data;
    private JButton btnUndo;
    private int currentPlayer;
    
    private static final int PLAYER1 = 1;
    private static final int PLAYER2 = 2;
    
    public MancalaBoard(GameData data)
    {
        currentPlayer = 1;
        this.data = data;
        this.setLayout(new BorderLayout());
        
        btnUndo = new JButton("Undo");
        btnUndo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            { 
                
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    
    private void playerMove(int currentPlayer, int pitNum)
    {
        if (currentPlayer == PLAYER1)
            currentPlayer = PLAYER2;
        else
            currentPlayer = PLAYER2;
        
        data.update(currentPlayer, pitNum);
    }
    
    @Override
    public void stateChanged(ChangeEvent e)
    {
        
    }
}
