package ru.cft.test.sortIt.sort;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Sort<T> {
    private final String[] filePassArray;
    private ArrayList<Scanner> scanners;
    private Comparator<T> comparator;

    public Sort(String[] passArray, String mode) {
        filePassArray = passArray;
        scanners = new ArrayList<>();

        for (String s : passArray) {
            try {
                scanners.add(new Scanner(new FileInputStream(s)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        createComparator(mode);
    }

    public void sort() {
        ArrayList<Integer> list = new ArrayList<>();
        scanners.forEach(e -> list.add(e.nextInt()));

        T data;
        T lastData = null;
        int index;

        while (!list.isEmpty()) {
            index = 0;
            //noinspection unchecked
            data = (T) list.get(0);

            for (int i = 0; i < list.size(); i++) {
                //noinspection unchecked
                if (comparator.compare((T) list.get(i), data) < 0) {
                    //noinspection unchecked
                    data = (T) list.get(i);
                    index = i;
                }
            }

            if (comparator.compare(lastData, data) <= 0) {
                System.out.println(data);
                lastData = data;
            } else {
                System.out.println("Ошибка! Нарушен порядок сортировки в документе: " + filePassArray[index] +
                        "\n Элемент: " + data + " - пропущен");
            }

            if (scanners.get(index).hasNext()) {
                list.set(index, scanners.get(index).nextInt());
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

            if (mode.equals("-d")) {
                @SuppressWarnings("unchecked")
                Comparable<T> object2 = (Comparable<T>) o2;

                return object2.compareTo(o1);
            }

            @SuppressWarnings("unchecked")
            Comparable<T> object1 = (Comparable<T>) o1;

            return object1.compareTo(o2);
        };
    }
}
