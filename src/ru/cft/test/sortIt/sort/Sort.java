package ru.cft.test.sortIt.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Sort<T> {
    Scanner[] scanners;

    public Sort(Scanner[] scanners) {
        this.scanners = Arrays.copyOf(scanners, scanners.length);
    }

    public T[] sort() {
        ArrayList<T> arList = new ArrayList<>();

        T[] dataArray = (T[]) new Object[scanners.length];;

        for (int i = 0; i < scanners.length; i++) {
            dataArray[i] = readNext(i);
        }


    }

    private T readNext(int index) {
        return (T) scanners[index].next();
    }
}
