import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import java.util.Random;

public class CatExample02 {

    public static void main(String[] args) throws Exception {

        for(int i=0; i<100;i++) {

            int nextInt = new Random().nextInt(3);

            System.out.println(String.format("loop %d, nextInt is %d", i, nextInt));

            if (nextInt % 3 == 0) {
                Cat.logEvent("my-event-type", "my-event-name-1", Event.SUCCESS, "id=123");
            } else if (nextInt % 3 == 1) {
                Cat.logEvent("my-event-type", "my-event-name-2", Event.SUCCESS, "id=1234");
            } else {
                Cat.logEvent("my-event-type", "my-event-name-2", "1", "id=1234");
            }

            Thread.sleep(1000); // 加上这句，不然上报不了，上报太快会被cat拦掉
        }

    }

}
