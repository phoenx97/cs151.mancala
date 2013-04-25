
import java.util.*;
import javax.swing.event.*;

/**
 *
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class GameData
{
    public static final int PLAYER1_PIT = 0;
    public static final int PLAYER2_PIT = 7;
    
    private static final int NUM_PITS = 14;
    
    private int[] pits;
    private ArrayList<ChangeListener> listeners;
    
    public GameData(int startingStones)
    {
        pits = new int[NUM_PITS];
        listeners = new ArrayList<ChangeListener>();
        
        for (int i = 0; i < pits.length; i++)
            if (i != PLAYER1_PIT && i != PLAYER2_PIT)
                pits[i] = startingStones;
        
        // debug
        System.out.println("Starting board:");
        for (int i = 0; i < pits.length; i++)
            System.out.println(i + ": " + pits[i]);
    }
    
    public int[] getData() { return (int[]) pits.clone(); }
    
    public void attach(ChangeListener c) { listeners.add(c); }
    
    public boolean update(int playerNum, int pitNum)
    {
        /* 
         * player num and pit have to be on same side (check by GUI?)
         * player 1 (pit 0) has pits 1-6
         * player 2 (pit 7) has pits 8-13
         */
        boolean freeturn = false;
        int opponent;
        int player;
        
        if (playerNum == 1)
        {
            player = PLAYER1_PIT;
            opponent = PLAYER2_PIT;
        }
        else
        {
            player = PLAYER2_PIT;
            opponent = PLAYER1_PIT;
        }
        
        int stones = pits[pitNum];
        int currentPit = pitNum + 1;
        
        pits[pitNum] = 0;
        
        for (int i = stones; i > 0; i--)
        {
            if (currentPit >= pits.length)
                    currentPit = 0; // reached end of board, start back at player 1 mancala
            
            if (currentPit == opponent)
            {
                i++; // skip the pit, keep the current i by incrementing
                currentPit++;
            }
            else
            {
                pits[currentPit]++;
                
                if (currentPit == player && i == 1) // last stone dropped was in own mancala
                    freeturn = true;
                
                currentPit++;
            }
        }
        
        // not sure where undo goes yet
        
        // debug
        System.out.println("New board:");
        for (int i = 0; i < pits.length; i++)
            System.out.println(i + ": " + pits[i]);
        
        for (ChangeListener l : listeners) 
            l.stateChanged(new ChangeEvent(this));
        
        return freeturn;
    }
}
