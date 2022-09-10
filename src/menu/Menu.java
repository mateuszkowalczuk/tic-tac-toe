package menu;

import gameplay.Input;
import gameplay.multiplayer.Multiplayer;
import gameplay.singleplayer.Singleplayer;

public class Menu {
    private boolean gameOn;
    private final Input input;

    public Menu() {
        input = new Input();
        gameOn = true;
        menu();
    }

    private void menu() {
        while (gameOn) {
            System.out.print("""
                    ========== tic-tac-toe ==========
                    1. Singleplayer
                    2. Multiplayer
                    3. Exit
                    Choose option: \s"""
            );
            gameOn = modeChoice(numberChoice());
        }
    }

    private boolean modeChoice(int chosenNumber) {
        if (chosenNumber == 1) new Singleplayer();
        else if (chosenNumber == 2) new Multiplayer();
        return chosenNumber != 3;
    }

    private int numberChoice() {
        int chosenNumber = 0;
        while (numberCheck(chosenNumber)) {
            chosenNumber = input.enterNumber();
            if (numberCheck(chosenNumber)) System.out.print("Incorrect number. Try again: ");
        }
        return chosenNumber;
    }

    private boolean numberCheck(int chosenNumber) {
        return chosenNumber <= 0 || chosenNumber >= 4;
    }
}