package ru.khamzin.sorted;

import ru.khamzin.entity.SortingMode;

import java.util.ArrayList;
import java.util.List;

// Классическая сортировка слиянием, но добавил возможность сортировки по возрастанию и убыванию
public class MergeSorter<T extends Comparable> {

    public List<T> sort(List<T> data, SortingMode sortingMode) {
        if (data.size() <= 1) {
            return data;
        }

        int middle = data.size() / 2;

        List<T> left = new ArrayList<>(data.subList(0, middle));
        List<T> right = new ArrayList<>(data.subList(middle, data.size()));

        left = sort(left, sortingMode);
        right = sort(right, sortingMode);

        return merge(left, right, sortingMode);
    }

    private List<T> merge(List<T> left, List<T> right, SortingMode sortingMode) {
        List<T> result = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            T leftItem = left.get(leftIndex);
            T rightItem = right.get(rightIndex);

            if (sortingMode == SortingMode.ASC) {
                if (leftItem.compareTo(rightItem) < 0) {
                    result.add(leftItem);
                    leftIndex++;
                }
                else {
                    result.add(rightItem);
                    rightIndex++;
                }
            }
            else if (sortingMode == SortingMode.DESC) {
                if (leftItem.compareTo(rightItem) > 0) {
                    result.add(leftItem);
                    leftIndex++;
                }
                else {
                    result.add(rightItem);
                    rightIndex++;
                }
            }
        }
        result.addAll(left.subList(leftIndex, left.size()));
        result.addAll(right.subList(rightIndex, right.size()));
        return result;
    }
}
