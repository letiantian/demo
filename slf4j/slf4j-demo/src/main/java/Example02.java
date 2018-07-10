import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Example02 {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Example01.class);
        MDC.put("IP", "123.123.123.123");
        logger.info("Hello {}", "World");
    }
}
