import java.util.*;
public class Game {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    private List<Integer> takenFieldsByPlayer = new ArrayList<>();
    private List<Integer> takenFieldsByCPU = new ArrayList<>();
    private String result = checkWinner();
    private int computerPosition = 0;
    private int playerPosition = 0;
    private char symbol = ' ';

    private  char[][] gameBoard = {  {' ', '|', ' ', '|', ' '},
                                    {'-', '+', '-', '+', '-'},
                                    {' ', '|', ' ', '|', ' '},
                                    {'-', '+', '-', '+', '-'},
                                    {' ', '|', ' ', '|', ' '}
    };
    public void displayGameBoard() {
        for (char[] game : gameBoard) {
            for (char g : game) {
                System.out.print(g);
            }
            System.out.println();
        }
    }
    public void field (char[][] gameBoard, String user, int position) {
        if (user.equals("player")) {
            symbol = 'X';
        } else if (user.equals("CPU")) {
            symbol = 'O';
        }
        switch (position) {
            case 1 -> gameBoard[0][0] = symbol;
            case 2 -> gameBoard[0][2] = symbol;
            case 3 -> gameBoard[0][4] = symbol;
            case 4 -> gameBoard[2][0] = symbol;
            case 5 -> gameBoard[2][2] = symbol;
            case 6 -> gameBoard[2][4] = symbol;
            case 7 -> gameBoard[4][0] = symbol;
            case 8 -> gameBoard[4][2] = symbol;
            case 9 -> gameBoard[4][4] = symbol;
        }
    }
    public String checkWinner() {
        List<List> winningList = new ArrayList<>();

        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> botRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1,5,9);
        List<Integer> cross2 = Arrays.asList(3,5,7);

        winningList.add(topRow);
        winningList.add(midRow);
        winningList.add(botRow);
        winningList.add(leftCol);
        winningList.add(midCol);
        winningList.add(rightCol);
        winningList.add(cross1);
        winningList.add(cross2);

        for (List l : winningList) {
            if (takenFieldsByPlayer.containsAll(l)) {
                return "You win!";
            } else if (takenFieldsByCPU.containsAll(l)) {
                return "CPU won. Sorry:(";
            } else if ( !takenFieldsByPlayer.containsAll(l) &&
                        !takenFieldsByCPU.containsAll(l) &&
                        (takenFieldsByCPU.size() + takenFieldsByPlayer.size() == 9)) {
                return "It's TIE!";
            }
        }
        return "";
    }
    public void play() {
        while (true) {
            System.out.print("Enter your place (1-9): ");

            try {
            playerPosition = getInt();
            while (takenFieldsByPlayer.contains(playerPosition) || takenFieldsByCPU.contains(playerPosition)) {
                System.out.print("Try again: ");
                playerPosition = scanner.nextInt();
            }
            field(gameBoard, "player", playerPosition);
            takenFieldsByPlayer.add(playerPosition);
            displayGameBoard();

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            computerPosition = random.nextInt(9) + 1;
            System.out.println("Computer move: ");
            while (takenFieldsByPlayer.contains(computerPosition) || takenFieldsByCPU.contains(computerPosition)) {
                computerPosition = random.nextInt(9) + 1;
            }
            field(gameBoard, "CPU", computerPosition);
            takenFieldsByCPU.add(computerPosition);
            displayGameBoard();
            System.out.println();

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            } catch (InputMismatchException e) {
                System.out.println("It is a string. Please enter a number between 1 and 9!\n");
        }
    }
}
    public static int getInt() {
        return new Scanner(System.in).nextInt();
    }
}




