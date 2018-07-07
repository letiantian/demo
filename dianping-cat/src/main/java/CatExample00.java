import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;

public class CatExample00 {

    public static void main(String[] args) throws Exception {

        for(int i=0; i<100;i++) {

            System.out.println("report " + i);

            report();

            Thread.sleep(100);
        }

    }

    public static void report() throws Exception {
        Transaction t = Cat.getProducer().newTransaction("transaction-type", "your-transaction-name");
        try {
            businessOperation();
            Cat.getProducer().logEvent("your-event-type", "your-event-name", Event.SUCCESS, "keyValuePairs");
            t.setStatus(Transaction.SUCCESS);
        } catch (Exception e) {
            Cat.getProducer().logError(e);//用log4j记录系统异常，以便在Logview中看到此信息
            t.setStatus(e);
            throw e;
        } finally {
            t.complete();
        }
    }

    public static void businessOperation() {

    }

}
