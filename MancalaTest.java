
/**
 * COPYRIGHT (C) 2013 All Rights Reserved
 * Runs the Mancala prorgram
 * @author Loveleen Kaur, Peter Le, Lashkar Singh
 * @version 1.0
 */
public class MancalaTest {
    public static void main(String[] args)
    {
        GameData model = new GameData();
        MancalaBoard board = new MancalaBoard(model);
        model.attach(board);
    }
}
