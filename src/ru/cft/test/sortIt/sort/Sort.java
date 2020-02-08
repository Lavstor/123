package ru.cft.test.sortIt.sort;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Sort {
    private ArrayList<Scanner> scanners;

    public Sort(String[] passArray) {
        scanners = new ArrayList<>();

        for (String s : passArray) {
            try {
                scanners.add(new Scanner(new FileInputStream(s)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void sort() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < scanners.size(); i++) {
            list.add( readNext(i));
        }

        int indexDelete = 0;
        while (!list.isEmpty()) {
            int nextData = list.get(indexDelete);

            for (int i = 0; i < list.size(); i++) {
                if (nextData < list.get(i)) {
                    nextData = list.get(i);
                    indexDelete = i;
                }
            }

            if (scanners.get(indexDelete).hasNext()) {
                list.set(indexDelete, readNext(indexDelete));
            } else{
                list.remove(indexDelete);
            }

            System.out.println(nextData);
        }
    }

    private int readNext(int index) {
        return scanners.get(index).nextInt();
    }
}
