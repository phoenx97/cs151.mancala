
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * Frame that contains the game board and all controllers
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class MancalaBoard extends JFrame implements ChangeListener
{
    private static final int PIT_SIZE = 100;
    private static final int STONE_SIZE = 15;
    
    private GameData data;
    private JButton buttonUndo;
    private JLabel labelCurrentPlayer;
    private JLabel labelUndoInfo;
    private Pit[] pits;
    
    /**
     * Creates the frame
     * @param data data model
     * @param startingStones number of stones in each pit to start
     */
    public MancalaBoard(final GameData data, int startingStones)
    {
        this.setLayout(new BorderLayout());
        this.data = data;
        this.pits = new Pit[GameData.NUM_PITS];
        int[] startingData = data.getData();
        
        // header that displays current player
        JPanel panelHeader = new JPanel(new FlowLayout());
        labelCurrentPlayer = new JLabel("Player " + data.getCurrentPlayer() + "'s turn");
        labelCurrentPlayer.setFont(new Font("Dialog", Font.BOLD, 24));
        panelHeader.add(labelCurrentPlayer);
        
        // actual board panel
        JPanel panelPits = new JPanel(new GridBagLayout());
        GridBagConstraints pitConstraints = new GridBagConstraints();
        pitConstraints.ipadx = 10;
        pitConstraints.ipady = 20;
        pitConstraints.gridy = 0;
        int j = GameData.PLAYER2_PIT - 1;
        for (int i = 0; i < GameData.PLAYER1_PIT; i++)
        {
            Pit tempPit = new CirclePit(new CircleStone(STONE_SIZE), PIT_SIZE);
            pitConstraints.gridx = i + 1;
            JLabel lbl = new JLabel(tempPit);
            
            final int idx = j;
            lbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent event) { pickPit(idx);}
            });
            
            panelPits.add(lbl, pitConstraints);
            pits[j--] = tempPit;
        }
        pitConstraints.gridy = 1;
        for (int i = 0; i < GameData.PLAYER1_PIT; i++)
        {
            Pit tempPit = new CirclePit(new CircleStone(STONE_SIZE), PIT_SIZE);
            pitConstraints.gridx = i + 1;
            JLabel lbl = new JLabel(tempPit);
            
            final int idx = i;
            lbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent event) { pickPit(idx);}
            });
            
            panelPits.add(lbl, pitConstraints);
            pits[i] = tempPit;
        }
        pitConstraints.gridy = 0;
        pitConstraints.gridx = 0;
        pitConstraints.gridheight = 2;
        Pit player1Pit = new CircleMancala(new CircleStone(STONE_SIZE), PIT_SIZE);
        Pit player2Pit = new CircleMancala(new CircleStone(STONE_SIZE), PIT_SIZE);
        pits[GameData.PLAYER1_PIT] = player1Pit;
        pits[GameData.PLAYER2_PIT] = player2Pit;
        JLabel lblPlayer1Pit = new JLabel(player1Pit);
        JLabel lblPlayer2Pit = new JLabel(player2Pit);
        panelPits.add(lblPlayer2Pit, pitConstraints);
        pitConstraints.gridx = GameData.PLAYER1_PIT + 1;
        panelPits.add(lblPlayer1Pit, pitConstraints);
        for (int i = 0; i < pits.length; i ++)
            pits[i].setStones(startingData[i]);
        
        // status panel at the bottom for undo
        JPanel panelStatus = new JPanel(new FlowLayout());
        labelUndoInfo = new JLabel(data.getUndosLeft() + " undos left for Player " + data.getLastPlayerUndo());
        buttonUndo = new JButton("Undo");
        buttonUndo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) { undo(); }
        });
        panelStatus.add(labelUndoInfo);
        panelStatus.add(buttonUndo);
        
        this.setTitle("Mancala");
        this.add(panelHeader, BorderLayout.PAGE_START);
        this.add(panelPits, BorderLayout.CENTER);
        this.add(panelStatus, BorderLayout.PAGE_END);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    
    /**
     * Selects a pit for player's turn
     * @param pit the pit to select
     */
    private void pickPit(int pit)
    {
        int status = data.update(pit);
        if (status == GameData.STATUS_P1_WIN)
        {
            labelCurrentPlayer.setText("Game over. Player 1 wins");
            labelUndoInfo.setText("");
            buttonUndo.setVisible(false);
            JOptionPane.showMessageDialog(this, "Player 1 (" + data.getPlayer1Score() + ") beat Player 2 (" + data.getPlayer2Score() + ")", "Game Over", JOptionPane.PLAIN_MESSAGE);
        }
        else if (status == GameData.STATUS_P2_WIN)
        {
            labelCurrentPlayer.setText("Game over. Player 2 wins");
            labelUndoInfo.setText("");
            buttonUndo.setVisible(false);
            JOptionPane.showMessageDialog(this, "Player 2 (" + data.getPlayer2Score() + ") beat Player 1 (" + data.getPlayer1Score() + ")", "Game Over", JOptionPane.PLAIN_MESSAGE);
        }
        else if (status == GameData.STATUS_DRAW)
        {
            labelCurrentPlayer.setText("Game over. Draw");
            labelUndoInfo.setText("");
            buttonUndo.setVisible(false);
            JOptionPane.showMessageDialog(this, "Game ended in a tie", "Game Over", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    /**
     * Attempt to undo the last action
     */
    private void undo() { data.undo(); }
    
    /**
     * Updates the view when the data changes
     * @param e change event
     */
    @Override
    public void stateChanged(ChangeEvent e)
    {
        int[] board = data.getData();
        for (int i = 0; i < board.length; i++)
            pits[i].setStones(board[i]);
        labelCurrentPlayer.setText("Player " + data.getCurrentPlayer() + "'s turn");
        labelUndoInfo.setText(data.getUndosLeft() + " undos left for Player " + data.getLastPlayerUndo());
        if (data.getUndosLeft() == 0)
            buttonUndo.setEnabled(false);
        else
            buttonUndo.setEnabled(true);
        repaint();
    }
}
