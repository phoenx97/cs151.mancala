
import javax.swing.*;

/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * Interface for the pits on the Mancala board
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public interface Pit extends Icon
{
    /**
     * Assigns number of stones inside the pit
     * @param numStones the number of stones
     */
    void setStones(int numStones);
}
