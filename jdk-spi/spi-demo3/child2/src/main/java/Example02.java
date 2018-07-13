import com.example.child1.spi.DemoService;
import java.util.List;

public class Example02 {

    public static void main(String[] args)  throws Exception {
        List<DemoService> serviceImplList = CustomServiceLoader.load(DemoService.class);

        for (DemoService impl: serviceImplList) {
            System.out.println(String.format("class: %s, result: %s ", impl.getClass().getName(), impl.sayHi("World")));
        }

    }
}
