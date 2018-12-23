package lang3;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import static tool.DemoUtils.println;

public class RandomUtilsTest {

    /**
     * 随机布尔值
     */
    @Test
    public void test_nextBoolean_01() {
        for(int i=0; i < 10; ++i) {
            println(RandomUtils.nextBoolean());
        }
    }

    /**
     * 随机数 -  int 类型
     */
    @Test
    public void test_nextInt_01() {
        for(int i=0; i < 10; ++i) {
            println(RandomUtils.nextInt());
        }
    }

    /**
     * [1, 100) 范围内的随机数  - int 类型
     */
    @Test
    public void test_nextInt_02() {
        for(int i=0; i < 10; ++i) {
            println(RandomUtils.nextInt(1, 100));
        }
    }

    /**
     * 随机数 -  long 类型
     */
    @Test
    public void test_nextLong_01() {
        for(int i=0; i < 10; ++i) {
            println(RandomUtils.nextLong());
        }
    }

    /**
     * [1, 100) 范围内的随机数 - long 类型
     */
    @Test
    public void test_nextLong_02() {
        for(int i=0; i < 10; ++i) {
            println(RandomUtils.nextLong(1, 100));
        }
    }

    /**
     * 随机数 -  float 类型
     */
    @Test
    public void test_nextFloat_01() {
        for(int i=0; i < 10; ++i) {
            println(RandomUtils.nextFloat());
        }
    }

    /**
     * [1, 100] 范围内的随机数 - float 类型
     */
    @Test
    public void test_nextFloat_02() {
        for(int i=0; i < 10; ++i) {
            println(RandomUtils.nextFloat(1, 100));
        }
    }

    /**
     * 随机数 -  double 类型
     */
    @Test
    public void test_nextDouble_01() {
        for(int i=0; i < 10; ++i) {
            println(RandomUtils.nextDouble());
        }
    }

    /**
     * [1, 100] 范围内的随机数 - double 类型
     */
    @Test
    public void test_nextDouble_02() {
        for(int i=0; i < 10; ++i) {
            println(RandomUtils.nextDouble(1, 100));
        }
    }


}
