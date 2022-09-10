package gameplay.singleplayer.ai;

public class AIPlayer {
    private char opponentChar;
    private char AIChar;
    private char[][] board;
    private final AIMove move;
    private final AIRule rule;

    public AIPlayer() {
        move = new AIMove();
        rule = new AIRule();
    }

    public char[][] AIMove(char[][] gameBoard, char opponentPlayerChar) {
        opponentChar = opponentPlayerChar;
        AIChar = checkOpponentChar();
        board = gameBoard;
        return makeMove();
    }

    private char checkOpponentChar() {
        return (opponentChar == 'X')? 'O':'X';
    }

    private char[][] makeMove() {
        if (isBoardEmpty()) return startGame();
        return isAICloseToWin();
    }

   private boolean isBoardEmpty() {
        return rule.checkIsBoardEmpty(board);
    }

    private char[][] startGame() {
        return move.startGameMove(board, AIChar);
    }

    private char[][] isAICloseToWin() {
        if (isAIOccupiedTwoFieldAndOneFieldIsAvailable()) return aiCloseToWinMove();
        return isOpponentCloseToWin();
    }

    private boolean isAIOccupiedTwoFieldAndOneFieldIsAvailable() {
        return rule.checkNumberOfBusyAndAvailableFields(board, AIChar, 2, 1);
    }

    private char[][] aiCloseToWinMove() {
        return move.checkAndMakeMove(board, AIChar, 2, 1, false);
    }

    private char[][] isOpponentCloseToWin() {
        if (isOpponentOccupiedTwoFieldAndOneFieldIsAvailable()) return opponentCloseToWinMove();
        return isAIOccupiedOneFieldAndTwoFieldIsAvailable();
    }

    private boolean isOpponentOccupiedTwoFieldAndOneFieldIsAvailable() {
        return rule.checkNumberOfBusyAndAvailableFields(board, opponentChar, 2, 1);
    }

    private char[][] opponentCloseToWinMove() {
        return move.checkAndMakeMove(board, opponentChar,2,1, true);
    }

    private char[][] isAIOccupiedOneFieldAndTwoFieldIsAvailable() {
        if (isAIOccupiedOneFieldAndTwoFieldIsAvailableInOneConfiguration()) return addSecondPawnToOneOccupiedField();
        return isThreeFieldAvailable();
    }

    private boolean isAIOccupiedOneFieldAndTwoFieldIsAvailableInOneConfiguration() {
        return rule.checkNumberOfBusyAndAvailableFields(board, AIChar, 1, 2);
    }

    private char[][] addSecondPawnToOneOccupiedField() {
        return move.checkAndMakeMove(board, AIChar, 1, 2, false);
    }

    private char[][] isThreeFieldAvailable() {
        if (isThreeFieldInOneConfigurationAvailable()) return addPawnInConfigurationWithThreeAvailableFields();
        return move.addPawnToAvailableField(board, AIChar);
    }

    private boolean isThreeFieldInOneConfigurationAvailable() {
        return rule.checkNumberOfBusyAndAvailableFields(board, AIChar, 0, 3);
    }

    private char[][] addPawnInConfigurationWithThreeAvailableFields() {
        return move.isOnlyOneOpponentPawnOnField(board, AIChar);
    }
}