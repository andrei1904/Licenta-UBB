package runners;

import java.time.LocalDateTime;

public class DelayTaskRunner extends AbstractTaskRunner {

    public DelayTaskRunner(TaskRunner taskRunner) {
        super(taskRunner);
    }

    protected void afterExecution() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void executeOneTask() {
        super.executeOneTask();
    }
}
