
/**
 * 
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class Mancala {
    public static void main(String[] args)
    {
        int startingStones = 3; // temp value - will need to get this from GUI prompt
        
        GameData model = new GameData(startingStones);
        MancalaBoard board = new MancalaBoard(model);
        model.attach(board);
        
        // test with console output
        if (!model.update(2))
            System.out.println("Invalid move");
        if (!model.undo())
            System.out.println("Undo failed (none left)");
    }
}
