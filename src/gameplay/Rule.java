package gameplay;

public class Rule {
    private char[][] board;
    private char playerChar;
    private final GameBoard gameBoard;

    public Rule() {
        gameBoard = new GameBoard();
    }

    public boolean isFieldExist(int playerChoice) {
        return playerChoice >= 11 && playerChoice <= 13
                || playerChoice >= 21 && playerChoice <= 23
                || playerChoice >= 31 && playerChoice <= 33;
    }

    public boolean isFieldAvailable(char[][] board, int playerChoice) {
        String moveNumber = String.valueOf(playerChoice);
        return board[(Integer.parseInt(moveNumber.substring(0,1)))-1][(Integer.parseInt(moveNumber.substring(1,2)))-1] == '_';
    }

    public boolean resultCheck(char[][] gameBoard, char currentPlayerChar) {
        board = gameBoard;
        playerChar = currentPlayerChar;
        if (isWin()) return false;
        return isGameOn();
    }

    private boolean isWin() {
        for (int y = 0; y < board.length; y++)
            for (int x = 0; x < board[y].length; x++)
                if (isConfigurationWin(0,0,1,1,2,2,2,2)) return winner();
                else if (isConfigurationWin(2,0,1,1,0,2,0,2)) return winner();
                else if (isConfigurationWin(y,0, y,1,  y,2, y,2)) return winner();
                else if (isConfigurationWin(0, y,1, y,2, y,2, y)) return winner();
        return false;
    }

    private boolean isConfigurationWin(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {
        return board[p1][p2] == board[p3][p4] && board[p3][p4] == board[p5][p6] && board[p7][p8] != '_';
    }

    private boolean winner() {
        System.out.println("Player " + playerChar + " wins!");
        return true;
    }

    private boolean isGameOn() {
        for (char[] x : board)
            for (char y : x)
                if (y == '_') return true;
        System.out.println("Draw! Game over.");
        return false;
    }

    public void isEndGame(char[][] board, boolean gameOn) {
        if (!gameOn) gameBoard.showBoard(board);
        System.out.println();
    }
}