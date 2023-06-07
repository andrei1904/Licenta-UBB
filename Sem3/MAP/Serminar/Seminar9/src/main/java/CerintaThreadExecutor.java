import java.util.concurrent.*;

public class CerintaThreadExecutor {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
//        executor.execute(() -> {
//            for (int i = 9; i > 0; i -= 2) {
//                System.out.println(i);
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        executor.execute(() -> {
//            for (int i = 0; i <= 10; i += 2) {
//                System.out.println(i);
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        Future<Integer> result1 = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int suma = 0;
                for (int i = 9; i > 0; i -= 2) {
                    suma += i;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return suma;
            }
        });

        Future<Integer> result2 = executor.submit(() -> {
           int suma = 0;
           for (int i = 0; i < 10; i += 2) {
               suma += i;
               try {
                   Thread.sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           return suma;
        });


        System.out.println(result1.get() * result2.get());
        executor.shutdown();
    }

}
