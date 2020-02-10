package ru.cft.test.sortIt.main;

import ru.cft.test.sortIt.sort.Sort;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> passes = new LinkedList<String>();
        String mode = "-a";

        for (int i = 0; i < args.length; i++) {
            if (args[i].contains(".txt")) {
                passes.add(args[i]);
            }

            if (args[i].contains("-d")) {
                mode = "-d";
            }

            if (args[i].contains("-s")) {
                mode = "-s";
            }

            if (args[i].contains("-i")) {
                mode = "-i";
            }
        }

        new Sort(passes, mode).sort();
    }
}
