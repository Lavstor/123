package ru.cft.test.sortIt.main;

import ru.cft.test.sortIt.sort.Sort;

public class Main {
    public static void main(String[] args, String mode) {
        new Sort(args[1], args[2]).sort();
    }
}
