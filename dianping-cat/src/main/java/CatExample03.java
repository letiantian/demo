import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;

import java.util.Random;

public class CatExample03 {

    public static void main(String[] args) throws Exception {

        Transaction t = Cat.newTransaction("my-transaction", "my-method");

        int sleepMs = 100;

        int times =60;
        
        String eventType = "event-type";
        String eventName = "event-name-3";

        for(int i=0; i<times; i++) {
            System.out.println("1 - " + i);
            Cat.logEvent(eventType, eventName, Event.SUCCESS, "id=1234");
            Thread.sleep(sleepMs);
        }

//        for(int i=0; i<times; i++) {
//            System.out.println("2 - " + i);
//            Cat.logEvent(eventType, eventName, "1", "id=1234");
//            Thread.sleep(sleepMs);
//        }
//
//        for(int i=0; i<times; i++) {
//            System.out.println("3 - " + i);
//            Cat.logEvent(eventType, eventName, "123", "id=1234");
//            Thread.sleep(sleepMs);
//        }
//
//        for(int i=0; i<times; i++) {
//            System.out.println("4 - " + i);
//            Cat.logEvent(eventType, eventName, "a", "id=1234");
//            Thread.sleep(sleepMs);
//        }

        for(int i=0; i<times;i++) {
            System.out.println("5 - " + i);
            Cat.logEvent(eventType, eventName, "error", "id=1234");
            Thread.sleep(sleepMs);
        }

        t.complete();
    }

}
