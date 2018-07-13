import com.example.child1.spi.DemoService;
import java.util.Iterator;
import java.util.ServiceLoader;

public class Example01 {

    public static void main(String[] args)  {
        ServiceLoader<DemoService> serviceLoader = ServiceLoader.load(DemoService.class);
        Iterator<DemoService> it = serviceLoader.iterator();
        while (it.hasNext()) {
            DemoService demoService = it.next();
            System.out.println(String.format("class: %s, result: %s ", demoService.getClass().getName(), demoService.sayHi("World")));
        }
    }
}
