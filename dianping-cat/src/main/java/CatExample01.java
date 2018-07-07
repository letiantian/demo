import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;

import java.util.Random;

public class CatExample01 {

    public static void main(String[] args) throws Exception {

        for(int i=0; i<100;i++) {

            Transaction t = Cat.newTransaction("my-transaction", "my-method");

            Cat.logEvent("my-event-type", "my-event-name");

            int nextInt = new Random().nextInt(3);

            System.out.println(String.format("loop %d, nextInt is %d", i, nextInt));

            if (nextInt % 2 == 0) {
                t.setStatus(Transaction.SUCCESS);
            } else {
                t.setStatus(String.valueOf(nextInt));
            }

            t.complete();

            Thread.sleep(100); // 加上这句，不然上报不了，上报太快会被cat拦掉
        }

    }

}
