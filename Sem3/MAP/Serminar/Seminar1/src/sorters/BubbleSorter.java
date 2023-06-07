package sorters;


public class BubbleSorter extends AbstractSorter{

    @Override
    public int[] sort(int[] list) {
        boolean sorted = false;
        int temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < list.length - 1; i++) {
                if (list[i] > list[i+1]) {
                    temp = list[i];
                    list[i] = list[i+1];
                    list[i+1] = temp;
                    sorted = false;
                }
            }
        }
        return list;
    }


}
