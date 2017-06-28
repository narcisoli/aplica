import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Darts game
 */

class DartsTable extends Game {

    //Attributes

    private static ArrayList<Integer> coord = new ArrayList<>();
    private int x;
    private int y;
    private Random rand = new Random();

    //Methods

    //Setting the coordinates of our Darts table to know how is the score in a specific slice

    public void setCoordinates() {
        this.coord.add(0);
        this.coord.add(1, 2);
        this.coord.add(2, 1);
        this.coord.add(3, 3);
        this.coord.add(4, 1);
    }

    //asks the current position of a throwed darts by the player

    public int currentCoord(int index) {
        return this.coord.get(index);
    }

    //giving the X and Y coordinates of a throwed darts

    public int getX() {
        x = (rand.nextInt(5) + 1);
        return x;
    }

    public int getY() {
        y = (rand.nextInt(20) + 1);
        return y;
    }

    //the main game
    public void startGame() {

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("You have just selected Darts Game. Let's start.");

            System.out.println("Score: ");
            int maxScore = Integer.parseInt(rd.readLine());
            setMaxScore(maxScore);

            //setPlayersAttributes();
            setCoordinates();

            String winner;
            while (true) {

                int x = getX();
                int y = getY();
                int cScore = 0;
                System.out.println(getPlayerName() + "\n" + "Position: " + x + " , " + y);
                if (x == 5 && y == 21) {
                    cScore = 25;
                } else if (x == 6 && y == 22) {
                    cScore = 50;
                } else if (y <= 20 && x <= 4) {
                    cScore = currentCoord(x) * y;
                }
                if (y <= 22 && x <= 6) {
                    setScore(cScore);
                }

                cScore = getPlayerScore();
                System.out.println("Score: " + cScore);
                if (cScore >= maxScore) {
                    winner = getPlayerName();
                    System.out.println("Congratulations!\n" + "The winner is: " + winner);
                    break;
                }
                getTurn();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

