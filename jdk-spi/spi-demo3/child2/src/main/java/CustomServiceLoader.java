import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class CustomServiceLoader<S> {

    private static final String PREFIX = "META-INF/services/";

    // 读取 META-INF/services/ 下文件的内容，返回由每一行内容组成的List
    private static List<String> parseConfigFile(URL configURL) throws Exception {
        InputStream in = configURL.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
        ArrayList<String> names = new ArrayList<>();
        while(true) {
            String line = reader.readLine();
            if (line != null) {
                int ci = line.indexOf('#');  // `#`字符后的内容是注释
                if (ci >= 0) line = line.substring(0, ci);
                line = line.trim();
                if (line.length()>0) {  // 空行就不要了
                    names.add(line);
                }
            } else {
                break;
            }
        }
        return names;
    }

    // 得到参数 service 的所有实现类的实例
    public static <S> List<S> load(Class<S> service) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String fullName = PREFIX + service.getName();

        // 符合要求的文件可能不止一个
        Enumeration<URL> configs = ClassLoader.getSystemResources(fullName);

        List<S> instanceList = new ArrayList<>();

        while(configs.hasMoreElements()) {
            URL config = configs.nextElement();
            List<String> implClassNames = parseConfigFile(config);
            for(String impl: implClassNames) {
                Class<?> cls = Class.forName(impl, false, classLoader);
                S instance = service.cast(cls.newInstance());
                instanceList.add(instance);
            }
        }
        return instanceList;
    }

}
