package sorters;

public class QuickSoerter extends AbstractSorter {

    @Override
    public int[] sort(int[] list) {
        quicksort(list, 0, list.length - 1);
        return list;
    }

    void quicksort(int[] list, int low, int high)
    {
        if (low < high)
        {
            int i = partition(list, low, high);

            quicksort(list, low, i-1);
            quicksort(list, i+1, high);
        }
    }

    int partition(int[] list, int low, int high)
    {
        int pivot = list[high];
        int i = (low-1);
        for (int j=low; j<high; j++)
        {
            if (list[j] < pivot)
            {
                i++;
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        }

        int temp = list[i+1];
        list[i+1] = list[high];
        list[high] = temp;

        return i+1;
    }
}
