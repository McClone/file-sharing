package org.mcclone.support;

import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;
import groovy.text.TemplateEngine;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;

/**
 * @author McClone
 */
public class GroovyTemplateGenerator implements TemplateGenerator<String> {

    private TemplateEngine engine;

    public GroovyTemplateGenerator() {
        engine = new SimpleTemplateEngine();
    }

    @Override
    public String generator(String templatePath, Map data) throws Exception {
        Template template = engine.createTemplate(new ClassPathResource(templatePath).getURL());
        return template.make(data).toString();
    }

    public TemplateEngine getEngine() {
        return engine;
    }

    public void setEngine(TemplateEngine engine) {
        this.engine = engine;
    }
}
