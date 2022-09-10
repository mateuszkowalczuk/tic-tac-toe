package gameplay.singleplayer;

import gameplay.*;
import gameplay.singleplayer.ai.AIPlayer;

public class Singleplayer {
    private final char playerChar;
    private char startDecision;
    private char[][] board;
    private boolean gameOn;
    private boolean isPlayerStarts;
    private final GameBoard gameBoard;
    private final Move move;
    private final Rule rule;
    private final AIPlayer aiPlayer;
    private final Choice choice;
    private final Input input;

    public Singleplayer() {
        System.out.println("\n===== Singleplayer =====\n");
        gameBoard = new GameBoard();
        move = new Move();
        rule = new Rule();
        aiPlayer = new AIPlayer();
        choice = new Choice();
        input = new Input();
        board = gameBoard.createGameBoard();
        playerChar = choice.playerCharChoice();
        isPlayerStarts = startCharChoice();
        gameOn = true;
        game();
    }

    private void game() {
        while (gameOn) {
            if (isPlayerStarts) {
                gameBoard.showBoard(board);
                board = move.playerMove(board, playerChar);
                gameOn = rule.resultCheck(board, playerChar);
            }
            if (gameOn) {
                gameBoard.showBoard(board);
                board = aiPlayer.AIMove(board, playerChar);
                gameOn = rule.resultCheck(board, choice.playerCharSwap(playerChar));
            }
            rule.isEndGame(board, gameOn);
            isPlayerStarts = true;
        }
    }

    private boolean startCharChoice() {
        boolean correctChar = false;
        while (!correctChar) {
            startDecision = charChoice();
            correctChar = decisionCharCheck();
            if (!correctChar) System.out.println("Incorrect char! Try again.");
        }
        return startDecision == 'Y' || startDecision == 'y';
    }

    private char charChoice() {
        System.out.print("Do you start the game? Enter Y(Yes) or N(No): ");
        return input.enterChar();
    }

    private boolean decisionCharCheck() {
        return startDecision == 'Y' || startDecision == 'N' ||
                startDecision == 'y' || startDecision == 'n';
    }
}