package ru.cft.test.sortIt.sort;

import java.io.*;
import java.util.*;

public class Sort {
    private ArrayList<Scanner> scanners;
    private Comparator<String> comparator;
    private String outFileName;

    private final String dataType;
    private final String sortingMode;
    private final String[] passes;

    public Sort(String sortingMode, String dataType, String outFileName, String[] passes) {
        this.outFileName = outFileName;

        if (dataType.equals("-s") || dataType.equals("-i")) {
            this.dataType = dataType;
        } else {
            throw new IllegalArgumentException("Направильный параметр тип данных!");
        }

        if (sortingMode.equals("-d")) {
            this.sortingMode = sortingMode;
        } else {
            this.sortingMode = "-a";
        }

        if (passes.length == 0) {
            throw new IllegalArgumentException("Необходимо не менее одного имени входного файла!");
        }

        if (outFileName.isEmpty()) {
            throw new IllegalArgumentException("Укажите название выходного файла!");
        }

        this.passes = Arrays.copyOf(passes, passes.length);
        scanners = new ArrayList<>();

        for (String fileName : passes) {
            try {
                scanners.add(new Scanner(new FileInputStream(fileName)));
            } catch (FileNotFoundException e) {
                System.out.println("не найден файл: " + fileName);
            }
        }

        createComparator();
    }

    public void sort() {
        try (FileWriter out = new FileWriter(outFileName)) {
            try {
                sort(out);
            } catch (IOException e) {
                sort();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Неправильный выходной путь! Создается out.txt");
            outFileName = "Out.txt";
            sort();
        } catch (IOException e) {
            System.out.println("Фобработка файла провалилась! Создается out.txt");
            outFileName = "Out.txt";
            sort();
        }
    }

    private void sort(FileWriter out) throws IOException {
        ArrayList<String> list = new ArrayList<>(scanners.size());
        String data;
        String lastData = null;

        for (int i = 0; i < scanners.size(); i++) {
            String nextLine = readNext(i);

            if (nextLine != null) {
                list.add(nextLine);
            } else {
                i--;
            }
        }

        int index;

        while (!list.isEmpty()) {
            index = 0;
            data = list.get(0);

            for (int i = 0; i < list.size(); i++) {
                if (comparator.compare(list.get(i), data) < 0) {
                    data = list.get(i);
                    index = i;
                }
            }

            if (lastData == null) {
                lastData = data;
            }

            if (comparator.compare(lastData, data) <= 0) {
                out.write(data + "\n");

                lastData = data;
            } else {
                System.out.println("Ошибка! Нарушен порядок сортировки в документе: " + passes[index] +
                        "\n Элемент: " + data + " - пропущен");
            }

            String nextList = readNext(index);

            if (nextList != null) {
                list.set(index, nextList);
            } else {
                list.remove(index);
            }
        }
    }

    private boolean intCheck(String line) {
        try {
            Integer.valueOf(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String readNext(int index) {
        while (scanners.get(index).hasNext()) {
            String nextList = scanners.get(index).nextLine();

            if (intCheck(nextList) || dataType.equals("-s")) {
                return nextList;
            }
        }

        scanners.remove(index);
        return null;
    }

    private void createComparator() {
        this.comparator = (o1, o2) -> {
            if (dataType.equals("-i")) {
                return sortingMode.equals("-d") ? Integer.valueOf(o2).compareTo(Integer.valueOf(o1)) :
                        Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
            }

            return sortingMode.equals("-d") ? o2.compareTo(o1) : o1.compareTo(o2);
        };
    }
}
