
/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class MancalaTest {
    public static void main(String[] args)
    {
        int startingStones = 3; // temp value - will need to get this from GUI prompt
        
        GameData model = new GameData(startingStones);
        MancalaBoard board = new MancalaBoard(model);
        model.attach(board);
        
        // test with console output
        model.update(2);
        // model.undo();
    }
}
