import domain.MessageTask;
import domain.SortingTask;
import enums.SortingStrategy;
import enums.Strategy;
import runners.DelayTaskRunner;
import runners.PrinterTaskRunner;
import runners.StrategyTaskRunner;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        // sorting task
        SortingTask[] sortingTaskArray = createSortingTaskArray();
        for (SortingTask st : sortingTaskArray) {
            st.execute();
        }


        // runners
        var v = createMessageTaskArray();

        StrategyTaskRunner strategyTaskRunner = new StrategyTaskRunner(Strategy.FIFO);
        strategyTaskRunner.addTask(v[0]);
        strategyTaskRunner.addTask(v[1]);
        strategyTaskRunner.addTask(v[2]);
//        strategyTaskRunner.executeAll();

        DelayTaskRunner delayTaskRunner = new DelayTaskRunner(strategyTaskRunner);
        delayTaskRunner.executeAll();

        PrinterTaskRunner printerTaskRunner = new PrinterTaskRunner(strategyTaskRunner);
//        printerTaskRunner.executeAll();


    }


    public static SortingTask[] createSortingTaskArray() {
        SortingTask t1 = new SortingTask("1", "Feedback lab1", new int[]{4, 3, 2, 1}
                , SortingStrategy.BUBBLE_SORT);
        SortingTask t2 = new SortingTask("2", "Feedback lab1", new int[]{1, 9, 4, 2}
                , SortingStrategy.BUBBLE_SORT);
        SortingTask t3 = new SortingTask("3", "Feedback lab1", new int[]{1, 3, 2, 4}
                , SortingStrategy.QUICK_SORT);
        SortingTask t4 = new SortingTask("4", "Feedback lab1", new int[]{9, 8, 7, 2}
                , SortingStrategy.QUICK_SORT);

        return new SortingTask[]{t1, t2, t3, t4};
    }

        public static MessageTask[] createMessageTaskArray() {
        MessageTask t1 = new MessageTask("1", "Feedback lab1",
                "Ai obtinut 9.60", "Gigi", "Ana", LocalDateTime.now());
        MessageTask t2 = new MessageTask("2", "Feedback lab1",
                "Ai obtinut 5.60", "Gigi", "Ana", LocalDateTime.now());
        MessageTask t3 = new MessageTask("3", "Feedback lab3",
                "Ai obtinut 10", "Gigi", "Ana", LocalDateTime.now());
        return new MessageTask[]{t1, t2, t3};
    }
}
