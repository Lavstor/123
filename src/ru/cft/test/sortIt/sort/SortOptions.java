package ru.cft.test.sortIt.sort;

import java.util.Scanner;

public class SortOptions {
    private Scanner[] scannerArray;

    public SortOptions(String[] passArray) {
        Scanner[] scannerArray = new Scanner[passArray.length];

        for (int i = 0; i < passArray.length; i++) {
            scannerArray[i] = new Scanner(passArray[i]);
        }


    }
}
