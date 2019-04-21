package demo01;


import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    @Test
    public void test() throws InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            System.out.println("Hello");
        });
        service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hi");
        });
        service.shutdown();
        System.out.println("线程池已关闭");
//        service.submit(() -> {
//            System.out.println("你好");
//        });
        service.awaitTermination(10, TimeUnit.SECONDS);
    }


    @Test
    public void test02() throws InterruptedException {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        System.out.println("开始前：" + getCurrentTimestamp());
        service.schedule(()-> {
            System.out.println( "1： " + getCurrentTimestamp() );
        }, 3, TimeUnit.SECONDS);

        service.schedule(()-> {
            System.out.println( "2：" + getCurrentTimestamp() );
        }, 2, TimeUnit.SECONDS);

        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);

    }



    private long getCurrentTimestamp() {
        return System.currentTimeMillis()/1000;
    }

}
