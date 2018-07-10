import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

public class Example01 {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Example01.class);
        logger.info("Hello {}", "World");
    }
}
