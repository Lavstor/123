package ru.cft.test.sortIt.main;

import ru.cft.test.sortIt.sort.Sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args[0].equals("-d")) {
            new Sort(args[0], args[1], args[2], Arrays.copyOfRange(args, 3, args.length)).sort();
        } else {
            new Sort("-a", args[0], args[1], Arrays.copyOfRange(args, 2, args.length)).sort();
        }
    }
}
