import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * Project Main class
 * We add all of the games in an ArrayList<Game>
 * The program asks the gamer about the type of the game he wants to play
 * We call the GameManager to start the chosen game
 *
 */
abstract class Main extends Game {
    public static void main(String[] args) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        //Adding the games in our ArrayList

        GameManager games = new GameManager();
        games.addGame(new KulamiGame());

        System.out.println("Welcome to Kulami game!\n");
        int index = Integer.parseInt(rd.readLine());

        //Calling the GameManager playGame method

        games.playGame(KulamiGame.class);
    }
}