package gameplay;

import java.util.Arrays;

public class GameBoard {
    private char[][] board;

    public char[][] createGameBoard() {
        board = setBoardSize();
        board = fillBoard();
        return board;
    }

    private char[][] setBoardSize() {
        return new char[3][3];
    }

    private char[][] fillBoard() {
        for (char[] place : board)
            Arrays.fill(place, '_');
        return board;
    }

    public void showBoard(char[][] board) {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++)
                if (x == 0 && y == 0) System.out.print("XY");
                else if (x == 0) System.out.print(" " + y);
                else if (y == 0) System.out.print(" " + x);
                else System.out.print(" " + board[y-1][x-1]);
            System.out.println();
        }
    }
}