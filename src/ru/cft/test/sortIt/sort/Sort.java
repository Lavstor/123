package ru.cft.test.sortIt.sort;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Sort<T> {
    private ArrayList<Scanner> scanners;
    private Comparator<T> comparator;

    public Sort(String[] passArray) {
        scanners = new ArrayList<>();

        for (String s : passArray) {
            try {
                scanners.add(new Scanner(new FileInputStream(s)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        createComparator();
    }

    public void sort() {
        //noinspection unchecked
        T[] dataArray = (T[]) new Object[scanners.size()];

        for (int i = 0; i < scanners.size(); i++) {
            dataArray[i] = readNext(i);
        }

        int indexDelete = 1;
        while (!scanners.isEmpty()) {

            T nextData = dataArray[indexDelete];

            for (int i = 0; i < dataArray.length; i++) {
                if (comparator.compare(nextData, dataArray[i]) > 0) {
                    nextData = dataArray[i];
                    indexDelete = i;
                }
            }

            if (scanners.get(indexDelete).hasNext()) {
                dataArray[indexDelete] = readNext(indexDelete);
            } else {
                dataArray[indexDelete] = null;
            }
            System.out.println(nextData);

            if(nextData == null){
                break;
            }


        }
    }

    private T readNext(int index) {
        //noinspection unchecked
        return (T) scanners.get(index).next();
    }

    private void createComparator() {
        this.comparator = (o1, o2) -> {
            if (o1 == null && o2 == null) {
                return 0;
            }

            if (o1 == null) {
                return -1;
            }

            if (o2 == null) {
                return 1;
            }

            @SuppressWarnings("unchecked")
            Comparable<T> object1 = (Comparable<T>) o1;

            return object1.compareTo(o2);
        };
    }
}
