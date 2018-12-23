package lang3;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import static tool.DemoUtils.println;

public class RandomStringUtilsTest {

    /**
     * 从所有支持的字符集中随机
     */
    @Test
    public void test_random_01() {
        println( RandomStringUtils.random(10) );
    }

    /**
     * 随机字母
     */
    @Test
    public void test_randomAlphabetic_01() {
        println( RandomStringUtils.randomAlphabetic(10) );  // 随机字符串长度为10
    }

    /**
     * 随机字母+数字
     */
    @Test
    public void test_randomAlphanumeric_01() {
        println( RandomStringUtils.randomAlphanumeric(20) );
    }

    /**
     * 随机数字
     */
    @Test
    public void test_randomNumeric_01() {
        println( RandomStringUtils.randomNumeric(10) );
    }

}
