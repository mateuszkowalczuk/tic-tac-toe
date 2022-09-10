package gameplay.singleplayer.ai;

public class AIMove {
    private final int randomX;
    private final int randomY;
    private int[] coordinates;
    private char[][] board;
    private char playerChar;
    private int pawnsNumber;
    private int emptyPlacesNumber;
    private int oneOppPawnX;
    private int oneOppPawnY;
    private int coordinateX;
    private int coordinateY;
    private int busyFieldsCounter;
    private int availableFieldsCounter;
    private boolean isCharSwap;
    private final AIRule rule;

    public AIMove() {
        randomX = (int)(Math.random() * 3);
        randomY = (int)(Math.random() * 3);
        coordinates = createTable();
        rule = new AIRule();
    }

    private int[] createTable() {
        return new int[2];
    }

    public char[][] startGameMove(char[][] board, char AIChar) {
        board[randomX][randomY] = AIChar;
        return board;
    }

    public char[][] addPawnToAvailableField(char[][] board, char AIChar) {
        for (int x = 0; x < board.length; x++)
            for (int y = 0; y < board[x].length; y++)
                if (board[x][y] == '_') {
                    board[x][y] = AIChar;
                    return board;
                }
        return board;
    }

    public char[][] checkAndMakeMove(char[][] gameBoard, char currentPlayerChar, int pawnNumber, int emptyPlaceNumber, boolean charSwap) {
        board = gameBoard;
        playerChar = currentPlayerChar;
        pawnsNumber = pawnNumber;
        emptyPlacesNumber = emptyPlaceNumber;
        isCharSwap = charSwap;
        coordinateX = 0;
        coordinateY = 0;
        if (rule.vertical(board, playerChar, pawnsNumber, emptyPlacesNumber)) return makeMove(coordinatesWhenAddPawnInVertical());
        else if (rule.horizontal(board, playerChar, pawnsNumber, emptyPlacesNumber)) return makeMove(coordinatesWhenAddPawnInHorizontal());
        else if (rule.skew(board, playerChar, pawnsNumber, emptyPlacesNumber)) return makeMove(coordinatesWhenAddPawnInSkew());
        else if (rule.antiSkew(board, playerChar, pawnsNumber, emptyPlacesNumber)) return makeMove(coordinatesWhenAddPawnInAntiSkew());
        return board;
    }

    private int[] coordinatesWhenAddPawnInVertical() {
        return coordinatesCheck(true);
    }

    private int[] coordinatesWhenAddPawnInHorizontal() {
        return coordinatesCheck(false);
    }

    private int[] coordinatesCheck(boolean isVertical) {
        for (int i = 0; i < 3; i++) {
            busyFieldsCounter = 0;
            availableFieldsCounter = 0;
            for (int j = 0; j < 3; j++) {
                if (isVertical) {
                    if (board[i][j] == playerChar) busyFieldsCounter++;
                    else if (board[i][j] == '_') {
                        availableFieldsCounter++;
                        coordinateX = i;
                        coordinateY = j;
                    }
                } else
                    if (board[j][i] == playerChar) busyFieldsCounter++;
                    else if (board[j][i] == '_') {
                        availableFieldsCounter++;
                        coordinateX = j;
                        coordinateY = i;
                    }
            }
            coordinates = checkCoordinates();
        }
        return coordinates;
    }

    private int[] coordinatesWhenAddPawnInSkew() {
        busyFieldsCounter = 0;
        availableFieldsCounter = 0;
        for (int j = 0; j < 3; j++) {
            if (board[j][j] == playerChar) busyFieldsCounter++;
            else if (board[j][j] == '_') {
                availableFieldsCounter++;
                coordinateX = j;
                coordinateY = j;
            }
            coordinates = checkCoordinates();
        }
        return coordinates;
    }

    private int[] coordinatesWhenAddPawnInAntiSkew() {
        busyFieldsCounter = 0;
        availableFieldsCounter = 0;
        for (int i = 0, j = 2; i < 3; i++, j--) {
            if (board[i][j] == playerChar) busyFieldsCounter++;
            else if (board[i][j] == '_') {
                availableFieldsCounter++;
                coordinateX = i;
                coordinateY = j;
            }
            coordinates = checkCoordinates();
        }
        return coordinates;
    }

    private int[] checkCoordinates() {
        if (busyFieldsCounter == pawnsNumber && availableFieldsCounter == emptyPlacesNumber) {
            coordinates[0] = coordinateX;
            coordinates[1] = coordinateY;
        }
        return coordinates;
    }

    private char[][] makeMove(int[] coordinates) {
        if (isCharSwap) playerChar = charSwap(playerChar);
        board[coordinates[0]][coordinates[1]] = playerChar;
        return board;
    }

    private char charSwap(char playerChar) {
        if (playerChar == 'X') return 'O';
        return 'X';
    }

    public char[][] isOnlyOneOpponentPawnOnField(char[][] board, char playerChar) {
        for (oneOppPawnX = 0; oneOppPawnX < board.length; oneOppPawnX++)
            for (oneOppPawnY = 0; oneOppPawnY < board[oneOppPawnX].length; oneOppPawnY++)
                if (board[oneOppPawnX][oneOppPawnY] != '_') return moveAfterFirstOpponentMove(board, playerChar);
        return board;
    }

    private char[][] moveAfterFirstOpponentMove(char[][] board, char playerChar) {
        if (rule.isNotOpponentPawnInCenter(oneOppPawnX, oneOppPawnY)) board[1][1] = playerChar;
        else board[--oneOppPawnX][oneOppPawnY] = playerChar;
        return board;
    }
}