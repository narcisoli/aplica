import java.util.ArrayList;
import java.util.Random;

public class Player {

    //Attributes
    private String name;
    private int score = 0;

    //Methods

    //Attributes of a player

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    //Giving a new score to a player

    public void setAddMoreScore(int currentScore) {
        score += currentScore;
    }

    //get the current score and current name of a player

    public int getCurrentPlayerScore() {
        return score;
    }

    public String getCurrentPlayerName() {
        return name;
    }



}