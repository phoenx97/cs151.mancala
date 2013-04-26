
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * COPYRIGHT (C) 2013 All Rights Reserved
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
        JPanel panelStatus = new JPanel(new FlowLayout());
        btnUndo = new JButton("Undo");
        btnUndo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            { 
                undo();
            }
        });
        lblCurrentPlayer = new JLabel("Player " + data.getCurrentPlayer() + "'s turn");
        panelStatus.add(lblCurrentPlayer, BorderLayout.PAGE_END);
        panelStatus.add(btnUndo, BorderLayout.PAGE_END);
        this.add(panelStatus);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    
    private void playerMove(int pitNum) { data.update(pitNum); }
    private void undo()
    {
        data.undo();
        repaint();
    }
    @Override
    public void stateChanged(ChangeEvent e)
    {
        lblCurrentPlayer.setText("Player " + data.getCurrentPlayer() + "'s turn");
        repaint();
    }
}
