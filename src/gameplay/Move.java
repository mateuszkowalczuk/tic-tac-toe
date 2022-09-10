package gameplay;

public class Move {
    private char[][] board;
    private char playerChar;
    private int playerChoice;
    private boolean isCorrectMove;
    private final Rule rule;
    private final Input input;

    public Move() {
        rule = new Rule();
        input = new Input();
    }

    public char[][] playerMove(char[][] gameBoard, char currentPlayerChar) {
        board = gameBoard;
        playerChar = currentPlayerChar;
        isCorrectMove = false;
        return move();
    }

    private char[][] move() {
        while (!isCorrectMove) {
            playerChoice = moveChoice();
            isCorrectMove = moveCheck();
            board = isCorrectChoice();
        }
        return board;
    }

    private int moveChoice() {
        System.out.print("Player " + playerChar + ", choose place (e.g. 13, that is x = 1 and y = 3): ");
        return input.enterNumber();
    }

    private boolean moveCheck() {
        if (rule.isFieldExist(playerChoice)) return rule.isFieldAvailable(board, playerChoice);
        return false;
    }

    private char[][] isCorrectChoice() {
        if (isCorrectMove) return saveMove();
        else System.out.println("Incorrect move. Try again!");
        return board;
    }

    private char[][] saveMove() {
        String moveNumber = String.valueOf(playerChoice);
        board[(Integer.parseInt(moveNumber.substring(0,1)))-1][(Integer.parseInt(moveNumber.substring(1,2)))-1] = playerChar;
        return board;
    }
}