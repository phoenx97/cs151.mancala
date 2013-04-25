
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
    private JLabel lblCurrentPlayer;
    
    public MancalaBoard(GameData data)
    {
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
        lblCurrentPlayer = new JLabel("Player " + data.getCurrentPlayer() + "'s turn");
        this.add(lblCurrentPlayer, BorderLayout.PAGE_END);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    
    private void playerMove(int pitNum) { data.update(pitNum); }
    
    @Override
    public void stateChanged(ChangeEvent e)
    {
        lblCurrentPlayer.setText("Player " + data.getCurrentPlayer() + "'s turn");
    }
}
