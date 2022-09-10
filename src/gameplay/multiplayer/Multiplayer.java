package gameplay.multiplayer;

import gameplay.GameBoard;
import gameplay.Choice;
import gameplay.Move;
import gameplay.Rule;

public class Multiplayer {
    private char playerChar;
    private char[][] board;
    private boolean gameOn;
    private final GameBoard gameBoard;
    private final Move move;
    private final Rule rule;
    private final Choice choice;

    public Multiplayer() {
        System.out.println("\n===== Multiplayer =====\n");
        gameBoard = new GameBoard();
        move = new Move();
        rule = new Rule();
        choice = new Choice();
        board = gameBoard.createGameBoard();
        playerChar = choice.playerCharChoice();
        gameOn = true;
        game();
    }

    private void game() {
        while (gameOn) {
            gameBoard.showBoard(board);
            board = move.playerMove(board, playerChar);
            gameOn = rule.resultCheck(board, playerChar);
            playerChar = choice.playerCharSwap(playerChar);
            rule.isEndGame(board, gameOn);
        }
    }
}