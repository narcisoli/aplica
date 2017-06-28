import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Games base class
 */
public abstract class Game {

    //Attributes

    private ArrayList<Player> players = new ArrayList<>();
    private int maxScore;
    public int turn = 0;

    //Methods:

    public void setPlayersAttributes()throws IOException
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Write number of players: ");
        int nrOfPlayers = keyboard.nextInt();
        for(int index = 0; index < nrOfPlayers; ++index)
        {
            System.out.print("Player nr. " + (index+1) + ": ");
            String playerName = br.readLine();
            players.add(index, new Player(playerName, 0));
        }
    }

    //The start of a game

    public void initGame() {
        try {
            setPlayersAttributes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        startGame();
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    //who's next?

    public int getTurn() {
        return turn = ((this.turn + 1) % players.size());
    }

    public String getPlayerName(){
        return players.get(turn).getCurrentPlayerName();
    }

    //set a given score to a player

    public void setScore(int cScore){
        players.get(turn).setAddMoreScore(cScore);
    }

    //get the score of a specific player

    public int getPlayerScore(){
        return players.get(turn).getCurrentPlayerScore();
    }


    public abstract void startGame();

    public abstract void setCoordinates();

}
