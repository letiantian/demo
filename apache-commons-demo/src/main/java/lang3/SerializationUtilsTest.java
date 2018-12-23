package lang3;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import static tool.DemoUtils.println;

import java.io.Serializable;

public class SerializationUtilsTest {

    static class Person implements Serializable {
        public String name;
        public int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    @Test
    public void test_clone_01() {
        Person p1 = new Person("xiaoming", 18);
        Person p2 = SerializationUtils.clone(p1);

        println(p1 == p2); // false
        println(p2.name);  // xiaoming
    }

}
