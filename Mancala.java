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
        
        model.update(1, 6); // test with console output
    }
}
