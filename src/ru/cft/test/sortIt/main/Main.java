package ru.cft.test.sortIt.main;

import ru.cft.test.sortIt.sort.SortOptions;

public class Main {
    public static void main(String[] args) {
        SortOptions sorting = new SortOptions(new String[]{args[0], args[1]});
    }
}
