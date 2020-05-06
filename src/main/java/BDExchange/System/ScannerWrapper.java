package BDExchange.System;

import java.util.Scanner;

public class ScannerWrapper {
    Scanner scanner = new Scanner(System.in);

    public String getNextLine() {
        return scanner.nextLine();
    }

    public int getNextInt() {
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }
}
