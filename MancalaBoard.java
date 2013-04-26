
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
        
        // will probably have to use something besides JLabel for graphics
        JPanel panelPits = new JPanel(new GridBagLayout());
        GridBagConstraints pitConstraints = new GridBagConstraints();
        pitConstraints.ipadx = 10;
        pitConstraints.ipady = 20;
        pitConstraints.gridy = 0;
        for (int i = 0; i < GameData.PLAYER1_PIT; i++)
        {
            pitConstraints.gridx = i + 1;
            JLabel lbl = new JLabel(new Pit(50));
            panelPits.add(lbl, pitConstraints);
        }
        pitConstraints.gridy = 1;
        //int j = GameData.PLAYER2_PIT - 1;
        for (int i = 0; i < GameData.PLAYER1_PIT; i++)
        {
            pitConstraints.gridx = i + 1;
            JLabel lbl = new JLabel(new Pit(50));
            panelPits.add(lbl, pitConstraints);
        }
        pitConstraints.gridy = 0;
        pitConstraints.gridx = 0;
        pitConstraints.gridheight = 2;
        JLabel lbl1 = new JLabel(new PlayerPit(50));
        panelPits.add(lbl1, pitConstraints);
        pitConstraints.gridx = GameData.PLAYER1_PIT + 1;
        JLabel lbl2 = new JLabel(new PlayerPit(50));
        panelPits.add(lbl2, pitConstraints);
        this.add(panelPits, BorderLayout.CENTER);
        
        JPanel panelStatus = new JPanel(new FlowLayout());
        btnUndo = new JButton("Undo");
        btnUndo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) { undo(); }
        });
        lblCurrentPlayer = new JLabel("Player " + data.getCurrentPlayer() + "'s turn");
        panelStatus.add(lblCurrentPlayer, BorderLayout.PAGE_END);
        panelStatus.add(btnUndo, BorderLayout.PAGE_END);
        this.setTitle("Mancala");
        this.add(panelStatus, BorderLayout.PAGE_END);
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
        int[] board = data.getData();
        lblCurrentPlayer.setText("Player " + data.getCurrentPlayer() + "'s turn");
        repaint();
    }
}
