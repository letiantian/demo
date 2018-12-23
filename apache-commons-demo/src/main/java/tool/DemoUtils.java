package tool;

import java.util.Arrays;

public class DemoUtils {

    public static void println(Object x) {
        System.out.println(x);
    }


    public static void printlnWithFormat(Object format, Object... args) {
        System.out.printf(format.toString(), args);
        System.out.println();
    }

}
