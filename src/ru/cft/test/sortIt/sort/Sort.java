package ru.cft.test.sortIt.sort;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Sort<T> {
    private ArrayList<Scanner> scanners;
    private Comparator<String> comparator;
    private String mode = "-a";

    private final LinkedList<String> passes;

    public Sort(LinkedList<String> passes, String mode) {
        this.passes = passes;
        scanners = new ArrayList<>();

        for (String s : passes) {
            try {
                scanners.add(new Scanner(new FileInputStream(s)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        createComparator(mode);
    }

    public void sort(String mode2) {
        ArrayList<String> list = new ArrayList<String>();;
        String data;

        scanners.forEach(e -> list.add(e.nextLine()));


        String lastData = null;
        int index;

        while (!list.isEmpty()) {
            index = 0;

            data = list.get(0);

            for (int i = 0; i < list.size(); i++) {

                if (comparator.compare(list.get(i),  data) < 0) {

                    data = list.get(i);
                    index = i;
                }
            }

            if (comparator.compare(lastData, data) <= 0) {
                System.out.println(data);
                lastData = data;
            } else {
                System.out.println("Ошибка! Нарушен порядок сортировки в документе: " + passes.get(index) +
                        "\n Элемент: " + data + " - пропущен");
            }

            if (scanners.get(index).hasNext()) {
                list.set(index, scanners.get(index).nextLine());
            } else {
                scanners.remove(index);
                list.remove(index);
            }
        }
    }

    private void createComparator(String mode) {
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

            return mode.equals("-d")?((Comparable<String>) o2).compareTo(o1):((Comparable<String>) o1).compareTo(o2);
        };
    }
}
