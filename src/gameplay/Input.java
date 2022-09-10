package gameplay;

import java.util.Scanner;

public class Input {
    Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    public int enterNumber() {
        return scanner.nextInt();
    }

    public char enterChar() {
        return scanner.next().charAt(0);
    }
}