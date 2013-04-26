
import java.util.*;
import javax.swing.event.*;

/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * Mancala
 * Data model for the game
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */

// weird case not sorted yet: invalid move followed by undo

public class GameData
{
    private static final int NUM_PITS = 14;
    private static final int MAX_UNDOS = 3;
    
    // indices for player's pits on the array
    public static final int PLAYER1_PIT = 6;
    public static final int PLAYER2_PIT = 13;
    
    public static final int INVALID = 0;
    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;
    public static final int STATUS_P1_WIN = 3;
    public static final int STATUS_P2_WIN = 4;
    public static final int STATUS_DRAW = 5;
    
    private int[] pits; // player 1 has pits 0-5, player 2 has pits 7-12
    private int[] lastPits; // last instance of the board before a move
    private int currentPlayer;
    private int undosLeft;
    private int lastPlayerUndo;
    private ArrayList<ChangeListener> listeners;

    /**
     * Constructs the initial game board data
     * 
     * @param startingStones number of stones in each pit to start the game
     */
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

    /**
     * Gets the board
     * @return array representing the mancala board
     */
    public int[] getData() { return (int[]) pits.clone(); }
    
    /**
     * Gets the current player
     * @return the current player
     */
    public int getCurrentPlayer() { return currentPlayer; }
    /**
     * Attaches a listener to this model
     * @param c listener to add
     */
    public void attach(ChangeListener c) { listeners.add(c); }
    
    /**
     * Perform a move on the board (pick from pit)
     * @param pitNum pit that the player chose
     * @return 0 if invalid, 1 if player 1's turn, 2 if player 2's, 3 if player 1 wins, 4 if player 2 wins, 5 if draw
     */
    public int update(int pitNum)
    {
        boolean valid = true;
        int opponent;
        int player;

        if (lastPlayerUndo != currentPlayer)
        {
            // reset undo counter when next player makes a move
            lastPlayerUndo = currentPlayer;
            undosLeft = MAX_UNDOS; 
        }

        if (currentPlayer == PLAYER1)
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
                (currentPlayer == PLAYER1 && pitNum > PLAYER1_PIT) || 
                (currentPlayer == PLAYER2 && pitNum < PLAYER1_PIT))
            valid = false;
        else
        {
            // perform the action
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
                } 
                else
                {
                    pits[currentPit]++;
                    if (currentPit == player && i == 1) // last stone dropped was in own mancala
                    {
                        freeturn = true;
                        System.out.println("Stone ended in player mancala. Free turn"); // debug
                    }
                    currentPit++;
                }
            }
            
            if (!freeturn) // don't change players if last stone ended in player's mancala
                nextPlayer();
        }
        
        if (valid)
        {
            // debug
            System.out.println("Updated board (Player " + currentPlayer + "'s turn):");
            for (int i = 0; i < pits.length; i++)
                System.out.println(i + ": " + pits[i]);
            
            sendUpdate();
            
            if (gameOver())
                return determineWinner();
            else
                return currentPlayer;
        }
        else
        {
            System.out.println("Invalid move"); // debug
            return INVALID;
        }
    }

    /**
     * Undoes the previous move (max 3 undos per player per turn)
     * @return player whose turn it is
     */
    public int undo()
    {
        boolean tempUndoDebug = false; // get rid of this later, obviously
        
        if (undosLeft > 0 && !Arrays.equals(lastPits, pits)) // make sure player has undos left and no consecutive undos allowed
        {
            tempUndoDebug = true; // get rid of this later, obviously
            
            pits = lastPits;
            if (lastPlayerUndo != currentPlayer) // case where player got last stone in own mancala
                nextPlayer();
            undosLeft--;

            // debug
            System.out.println("Undo performed. Updated board (Player " + currentPlayer + "'s turn):");
            for (int i = 0; i < pits.length; i++)
                System.out.println(i + ": " + pits[i]);

            sendUpdate();
        }
        
        //debug
        if (!tempUndoDebug)
            System.out.println("Undo failed - invalid");
        return currentPlayer;
    }
    
    /**
     * Game is over when one player's pits (not including their mancala) are empty
     * @return true if game is over, false otherwise
     */
    private boolean gameOver()
    {
        // check if either player's side has all empty pits
        boolean sideEmpty = true;
        if (currentPlayer == PLAYER1)
        {
            for (int i = 0; i < PLAYER1_PIT && sideEmpty; i++)
                if (pits[i] != 0)
                    sideEmpty = false;
        }
        else
        {
            for (int i = PLAYER1_PIT + 1; i < PLAYER2_PIT && sideEmpty; i++)
                if (pits[i] != 0)
                    sideEmpty = false;
        }
        // if so, get all the stones from opponent and place them in opponent mancala
        if (sideEmpty)
        {
            int p1stones = 0;
            int p2stones = 0;
            
            for (int i = 0; i < PLAYER1_PIT; i++)
            {
                p1stones += pits[i];
                pits[i] = 0;
            }
            for (int i = PLAYER1_PIT + 1; i < PLAYER2_PIT; i++)
            {
                p2stones += pits[i];
                pits[i] = 0;
            }

            pits[PLAYER1_PIT] += p1stones;
            pits[PLAYER2_PIT] += p2stones;
        }
        return sideEmpty;
    }
    
    /**
     * Compare the stones in each player's mancala and determine the winner
     * @return 3 if player 1 wins, 4 if player 2 wins, 5 if draw
     */
    private int determineWinner()
    {
        if (pits[PLAYER1_PIT] > pits[PLAYER2_PIT])
            return STATUS_P1_WIN;
        else if (pits[PLAYER1_PIT] < pits[PLAYER2_PIT])
            return STATUS_P2_WIN;
        else
            return STATUS_DRAW;
    }
    
    /**
     * Switches to the next player
     */
    private void nextPlayer()
    {
        if (currentPlayer == PLAYER1)
            currentPlayer = PLAYER2;
        else
            currentPlayer = PLAYER1;
    }
    
    /**
     * Send update to all attached ChangeListeners
     */
    private void sendUpdate()
    {
        for (ChangeListener l : listeners)
                l.stateChanged(new ChangeEvent(this));
    }
}
