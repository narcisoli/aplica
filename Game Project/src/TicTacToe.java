import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TicTacToe game
 **/

class TicTacToe extends Game {

    //Attributes

    private int x;
    private int y;
    private int dimensionOfTheTable;
    private ArrayList<ArrayList<Integer>> table = new ArrayList<>();

    //Methods

    //TicTacToe table dimension

    public void setDimensionOfTheTable(int dimensionOfTheTable) {
        this.dimensionOfTheTable = dimensionOfTheTable;
    }

    //set the table's coordinates

    public void setCoordinates() {
        for (int indexi = 0; indexi < dimensionOfTheTable; ++indexi) {
            table.add(new ArrayList<Integer>());
            for (int indexj = 0; indexj < dimensionOfTheTable; ++indexj) {
                table.get(indexi).add(new Integer(-1));
            }
        }
    }

    public void setCoordinatesXY(int x, int y) {

        table.get(x).set(y, turn);

    }

    public void printTable() {
        for (int indexi = 0; indexi < dimensionOfTheTable; ++indexi) {
            for (int indexj = 0; indexj < dimensionOfTheTable; ++indexj) {
                if (table.get(indexi).get(indexj) == 0)
                    System.out.print(" O   ");
                else if (table.get(indexi).get(indexj) == 1)
                    System.out.print(" X   ");
                else
                    System.out.print(table.get(indexi).get(indexj) + "   ");
            }
            System.out.print("\n");
        }
    }

    //Verification if one of the players has already won the game
    //row
    //column
    //diagonal

    public int verify() {

        for (int indexi = 0; indexi < dimensionOfTheTable; ++indexi) {
            int elementVerificatorRow = table.get(indexi).get(0);
            int elementVerificatorColumn = table.get(0).get(indexi);
            int rowVerification = 1;
            int columnVerification = 1;
            for (int indexj = 1; indexj < dimensionOfTheTable; ++indexj) {
                if (elementVerificatorRow != -1) {
                    if (elementVerificatorRow != table.get(indexi).get(indexj)) {
                        rowVerification = 0;
                    }
                }
                if (elementVerificatorColumn != -1) {
                    if (elementVerificatorColumn != table.get(indexj).get(indexi)) {
                        columnVerification = 0;
                    }
                }

            }
            if (rowVerification == 1)
                return elementVerificatorRow;
            if (columnVerification == 1)
                return elementVerificatorColumn;

        }

        int elementVerificatorDiagonal1 = table.get(0).get(0);
        int diagonal1Verification = 1;
        int elementVerificatorDiagonal2 = table.get(dimensionOfTheTable - 1).get(dimensionOfTheTable - 1);
        int diagonal2Verification = 1;
        for (int indexi = 1; indexi < dimensionOfTheTable; ++indexi) {

            if (elementVerificatorDiagonal1 != -1) {
                if (elementVerificatorDiagonal1 != table.get(indexi).get(indexi)) {
                    diagonal1Verification = 0;
                }
            }
            if (elementVerificatorDiagonal2 != -1) {
                if (elementVerificatorDiagonal2 == table.get(indexi).get(dimensionOfTheTable - 1 - indexi)) {
                    elementVerificatorDiagonal2 = 0;
                }
            }
        }
        if (diagonal1Verification == 1)
            return elementVerificatorDiagonal1;
        if (diagonal2Verification == 1)
            return elementVerificatorDiagonal2;

        return -1;
    }

    public void writePlayersScore(){
        System.out.println("Current score board: ");
        System.out.print(getPlayerName() + ": " + getPlayerScore() + " and ");
        getTurn();
        System.out.println(getPlayerName() + ": " + getPlayerScore());
        getTurn();

    }

    //The main game

    public void startGame() {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        Scanner keyboard = new Scanner(System.in);

        try {
            System.out.println("You have just selected Tic-Tac-Toe Game. Let's start.");
            System.out.println("Write max score: ");
            int maxScore = Integer.parseInt(rd.readLine());

            setMaxScore(maxScore);
            //setPlayersAttributes();
            setDimensionOfTheTable(3);
            setCoordinates();

            int scoreVerify1 = 0, scoreVerify2 = 0;
            int round = 1;
            while (scoreVerify1 < maxScore && scoreVerify2 < maxScore) {
                String winner;
                System.out.println("Round number: " + round);
                int nr = 0;
                while (nr < 9) {

                    System.out.println(getPlayerName() + " :");
                    System.out.print("Row: ");
                    x = keyboard.nextInt();
                    System.out.print("Column: ");
                    y = keyboard.nextInt();
                    setCoordinatesXY(x,y);

                    if (verify() != -1) {
                        winner = getPlayerName();
                        setScore(1);
                        scoreVerify1 = getPlayerScore();
                        getTurn();
                        scoreVerify2 = getPlayerScore();
                        System.out.println("Congratulations!\n" + "The winner is: " + winner);
                        printTable();
                        break;
                    }
                    printTable();
                    nr++;
                    getTurn();
                }
                if (nr >= 9)
                    System.out.println("It's a tie!");
                writePlayersScore();
                round++;

            }
        } catch (IOException ex) {
            System.out.print("Error at ticTacToeMain, ex: " + ex.toString());
        }
    }

}

