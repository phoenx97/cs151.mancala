
import java.util.*;
import javax.swing.event.*;

/**
 *
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */

// weird case not sorted yet: invalid move followed by undo

public class GameData
{
    // indices for player's pits on the array
    public static final int PLAYER1_PIT = 6;
    public static final int PLAYER2_PIT = 13;
    private static final int NUM_PITS = 14;
    private static final int MAX_UNDOS = 3;
    
    private int[] pits; // player1 has pits 1-6, player2 has pits 8-13
    private int[] lastPits;
    private int currentPlayer;
    private int undosLeft;
    private int lastPlayerUndo;
    private ArrayList<ChangeListener> listeners;

    public GameData(int startingStones)
    {
        pits = new int[NUM_PITS];
        currentPlayer = 1;
        lastPlayerUndo = 1;
        undosLeft = MAX_UNDOS;
        listeners = new ArrayList<ChangeListener>();

        for (int i = 0; i < pits.length; i++)
            if (i != PLAYER1_PIT && i != PLAYER2_PIT)
                pits[i] = startingStones;

        lastPits = pits.clone();
        
        // debug
        System.out.println("Starting board:");
        for (int i = 0; i < pits.length; i++)
            System.out.println(i + ": " + pits[i]);
    }

    public int[] getData()
    {
        return (int[]) pits.clone();
    }

    public void attach(ChangeListener c)
    {
        listeners.add(c);
    }

    public boolean update(int pitNum)
    {
        boolean valid = true;
        int opponent;
        int player;

        if (lastPlayerUndo != currentPlayer)
        {
            lastPlayerUndo = currentPlayer;
            undosLeft = MAX_UNDOS; // reset undo counter when next player makes a move
        }

        if (currentPlayer == 1)
        {
            player = PLAYER1_PIT;
            opponent = PLAYER2_PIT;
        } 
        else
        {
            player = PLAYER2_PIT;
            opponent = PLAYER1_PIT;
        }

        // player num and pit have to be on same side and pit cant be player's
        if (pitNum == player || pitNum == opponent || pits[pitNum] == 0 || 
                (currentPlayer == 1 && pitNum > PLAYER1_PIT) || 
                (currentPlayer == 2 && pitNum < PLAYER1_PIT))
            valid = false;
        else
        {
            boolean freeturn = false;
            lastPits = pits.clone(); // clone list for undo before making any changes

            int stones = pits[pitNum];
            int currentPit = pitNum + 1;

            pits[pitNum] = 0;

            for (int i = stones; i > 0; i--)
            {
                if (currentPit >= pits.length)
                    currentPit = 0; // reached end of board, start back at first position

                if (currentPit == opponent)
                {
                    i++; // skip the pit if it's the opponent's mancala, keep the current i
                    currentPit++;
                } else
                {
                    pits[currentPit]++;

                    if (currentPit == player && i == 1) // last stone dropped was in own mancala
                        freeturn = true;

                    currentPit++;
                }
                if (!freeturn)
                    nextPlayer();
            }

            // debug
            System.out.println("Updated board (Player " + currentPlayer + "'s turn):");
            for (int i = 0; i < pits.length; i++)
                System.out.println(i + ": " + pits[i]);

            for (ChangeListener l : listeners)
                l.stateChanged(new ChangeEvent(this));
        }
        return valid;
    }

    public boolean undo()
    {
        boolean allow = false;

        if (undosLeft > 0)
        {
            allow = true;
            pits = lastPits;
            if (lastPlayerUndo != currentPlayer) // case where player got last stone in own mancala
                nextPlayer();
            undosLeft--;

            // debug
            System.out.println("Undo performed. Updated board (Player " + currentPlayer + "'s turn):");
            for (int i = 0; i < pits.length; i++)
                System.out.println(i + ": " + pits[i]);

            for (ChangeListener l : listeners)
                l.stateChanged(new ChangeEvent(this));
        }

        return allow;
    }

    public int getUndosLeft()
    {
        return undosLeft;
    }

    public int getCurrentPlayer()
    {
        return currentPlayer;
    }

    private void nextPlayer()
    {
        if (currentPlayer == 1)
            currentPlayer = 2;
        else
            currentPlayer = 1;
    }
}
