package gameplay.singleplayer.ai;

public class AIRule {
    private int pawnCounter;
    private int emptyFieldsCounter;

    public boolean checkIsBoardEmpty(char[][] board) {
        for (char[] x : board)
            for (char y : x)
                if (y != '_') return false;
        return true;
    }

    public boolean checkNumberOfBusyAndAvailableFields(char[][] gameBoard, char currentPlayerChar, int pawnNumber, int emptyPlaceNumber) {
        return vertical(gameBoard, currentPlayerChar, pawnNumber, emptyPlaceNumber)
                || horizontal(gameBoard, currentPlayerChar, pawnNumber, emptyPlaceNumber)
                || skew(gameBoard, currentPlayerChar, pawnNumber, emptyPlaceNumber)
                || antiSkew(gameBoard, currentPlayerChar, pawnNumber, emptyPlaceNumber);
    }

    public boolean vertical(char[][] board, char playerChar, int pawnNumber, int emptyPlaceNumber) {
        return configurationCheck(true, board, playerChar, pawnNumber, emptyPlaceNumber);
    }

    public boolean horizontal(char[][] board, char playerChar, int pawnNumber, int emptyPlaceNumber) {
        return configurationCheck(false, board, playerChar, pawnNumber, emptyPlaceNumber);
    }

    private boolean configurationCheck(boolean isVertical, char[][] board, char playerChar, int pawnNumber, int emptyPlaceNumber) {
        for (int i = 0; i < 3; i++) {
            pawnCounter = 0;
            emptyFieldsCounter = 0;
            for (int j = 0; j < 3; j++)
                if (isVertical) {
                    if (board[i][j] == playerChar) pawnCounter++;
                    else if (board[i][j] == '_') emptyFieldsCounter++;
                } else
                if (board[j][i] == playerChar) pawnCounter++;
                else if (board[j][i] == '_') emptyFieldsCounter++;
            if (isCorrectConfiguration(pawnNumber, emptyPlaceNumber)) return true;
        }
        return false;
    }

    public boolean skew(char[][] board, char playerChar, int pawnNumber, int emptyPlaceNumber) {
        pawnCounter = 0;
        emptyFieldsCounter = 0;
        for (int i = 0; i < 3; i++)
            if (board[i][i] == playerChar) pawnCounter++;
            else if (board[i][i] == '_') emptyFieldsCounter++;
        return isCorrectConfiguration(pawnNumber, emptyPlaceNumber);
    }

    public boolean antiSkew(char[][] board, char playerChar, int pawnNumber, int emptyPlaceNumber) {
        pawnCounter = 0;
        emptyFieldsCounter = 0;
        for (int i = 0, j = 2; i < 3; i++, j--)
            if (board[i][j] == playerChar) pawnCounter++;
            else if (board[i][j] == '_') emptyFieldsCounter++;
        return isCorrectConfiguration(pawnNumber, emptyPlaceNumber);
    }

    private boolean isCorrectConfiguration(int pawnsNumber, int emptyPlacesNumber) {
        return pawnCounter == pawnsNumber && emptyFieldsCounter == emptyPlacesNumber;
    }

    public boolean isNotOpponentPawnInCenter(int oneOppPawnX, int oneOppPawnY) {
        return oneOppPawnX == 0 && oneOppPawnY == 0 || oneOppPawnX == 1 && oneOppPawnY == 0
                || oneOppPawnX == 2 && oneOppPawnY == 0 || oneOppPawnX == 0 && oneOppPawnY == 1
                || oneOppPawnX == 2 && oneOppPawnY == 1 || oneOppPawnX == 0 && oneOppPawnY == 2
                || oneOppPawnX == 1 && oneOppPawnY == 2 || oneOppPawnX == 2 && oneOppPawnY == 2;
    }
}