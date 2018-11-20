package freemarker;

import freemarker.cache.StringTemplateLoader;
import freemarker.cache.URLTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.DemoUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FreeMarkerDemo {

    /**
     * 渲染字符串
     *
     * @throws IOException
     * @throws TemplateException
     */
    @Test
    public void test_01() throws IOException, TemplateException {
        String templateName = "fm_001";
        // 将模板文件的内容读出来
        String templateContent = DemoUtil.readResource("templates/freemarker/fm_001.ftl");

        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate(templateName, templateContent);
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateLoader(stringLoader);
        Template template = cfg.getTemplate(templateName);
        StringWriter stringWriter = new StringWriter();
        BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("message", "你好");
        template.process(dataMap, bufferedWriter);
        String result = stringWriter.toString();
        bufferedWriter.flush();
        bufferedWriter.close();
        log.info("{}", result);
    }

    /**
     * 直接指定位置
     * @throws IOException
     * @throws TemplateException
     */
    @Test
    public void test_02() throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates"); // 不能是'/'或者空
        cfg.setDefaultEncoding("UTF-8");
        Template template = cfg.getTemplate("freemarker/fm_001.ftl");

        StringWriter stringWriter = new StringWriter();
        BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("message", "你好");
        template.process(dataMap, bufferedWriter);
        String result = stringWriter.toString();
        bufferedWriter.flush();
        bufferedWriter.close();
        log.info("{}", result);
    }

}
