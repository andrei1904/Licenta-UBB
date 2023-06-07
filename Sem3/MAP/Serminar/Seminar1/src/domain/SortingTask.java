package domain;

import enums.SortingStrategy;
import sorters.BubbleSorter;
import sorters.QuickSoerter;

import java.util.Arrays;


public class SortingTask extends Task {
    private int[] list;
    private final SortingStrategy sortingStrategy;

    public SortingTask(String taskId, String desc, int[] list, SortingStrategy sortingStrategy) {
        super(taskId, desc);
        this.list = list;
        this.sortingStrategy = sortingStrategy;
    }

    @Override
    public void execute() {
        switch (sortingStrategy) {
            case BUBBLE_SORT -> {
                list = new BubbleSorter().sort(list);
            }
            case QUICK_SORT -> {
                list = new QuickSoerter().sort(list);
            }
        };

        System.out.println(Arrays.toString(list));
    }
}
