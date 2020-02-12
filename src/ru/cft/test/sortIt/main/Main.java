package ru.cft.test.sortIt.main;

import ru.cft.test.sortIt.sort.Sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args.length > 3 && args[0].equals("-d")) {
            new Sort(args[0], args[1], args[2], Arrays.copyOfRange(args, 3, args.length)).sort();
        } else if (args.length > 2) {
            new Sort("-a", args[0], args[1], Arrays.copyOfRange(args, 2, args.length)).sort();
        } else {
            throw new IllegalArgumentException("Убедитесь что были введены все аргументы!");
        }
    }
}
