package velocity;

import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

import java.io.StringWriter;

@Slf4j
public class VelocityDemo {

    @Test
    public void test_01() {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        Template t = ve.getTemplate("templates/velocity/hello_001.vm");
        VelocityContext ctx = new VelocityContext();
        ctx.put("message", "你好");
        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);
        log.info("{}", sw.toString());
    }

    @Test
    public void test_02() {

    }
}
