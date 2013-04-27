
/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * Runs the Mancala prorgram
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class MancalaTest {
    public static void main(String[] args)
    {
        int startingStones = 3; // temp value - will need to get this from GUI prompt
        
        GameData model = new GameData(startingStones);
        MancalaBoard board = new MancalaBoard(model, startingStones);
        model.attach(board);
        
        /* test using console output
        model.update(3);
        model.update(0);
        model.update(9);
        model.update(2);
        model.update(1);
        model.update(8);
        model.update(2);
        model.update(9);
        model.update(3);
        model.update(7);
        model.update(5);
        model.update(7);
        model.update(4);
        model.update(7);
        model.update(5);
        */
    }
}
