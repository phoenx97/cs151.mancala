/**
 * 
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class Mancala {
    public static void main(String[] args)
    {
        int startingStones = 3;
        GameData model = new GameData(startingStones);
        
        model.update(1, 6);
    }
}
