import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by intern08 on 21.04.2017.
 */
public class GameManager {

    //Attributes
    public ArrayList<Game> games = new ArrayList<>();

    //Methods

    //adding a new game

    public void addGame(Game game) {
        games.add(game);
    }

    //Start the game, with type specification

    public void playGame(Class<?> gameType) {
        for (int index = 0; index < games.size(); ++index) {
            if (gameType == games.get(index).getClass()) {
                games.get(index).initGame();
            }
        }

    }

}