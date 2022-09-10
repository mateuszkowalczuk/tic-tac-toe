package gameplay;

public class Choice {
    private char playerChar;
    private boolean correctChar;
    private final Input input;

    public Choice() {
        input = new Input();
    }

    public char playerCharChoice() {
        correctChar = false;
        while (!correctChar) {
            playerChar = charChoice();
            correctChar = playerCharCheck();
            isCorrectChar();
        }
        return upperChar();
    }

    private char charChoice() {
        System.out.print("Starting player, choose your char (X/O): ");
        return input.enterChar();
    }

    private boolean playerCharCheck() {
        return playerChar == 'X' || playerChar == 'O' ||
                playerChar == 'x' || playerChar == 'o';
    }

    private void isCorrectChar() {
        if (!correctChar) System.out.println("Incorrect char! Try again.");
    }

    private char upperChar() {
        if (playerChar == 'x') return 'X';
        else if (playerChar == 'o') return 'O';
        return playerChar;
    }

    public char playerCharSwap(char playerChar) {
        if (playerChar == 'X') return 'O';
        return 'X';
    }
}