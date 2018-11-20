package util;

import com.google.common.base.Charsets;

import java.io.IOException;
import java.net.URL;

public class DemoUtil {

    /**
     * 读取 resources 文件内容
     * @param resource
     * @return
     */
    public static String readResource(String resource) {
        URL url = com.google.common.io.Resources.getResource(resource);
        try {
            return com.google.common.io.Resources.toString(url, Charsets.UTF_8);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
