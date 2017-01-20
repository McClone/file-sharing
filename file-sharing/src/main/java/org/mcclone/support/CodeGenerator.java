package org.mcclone.support;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mcclone on 17-1-19.
 */
public class CodeGenerator {

    public static void main(String[] args) throws Exception {
        TemplateGenerator generator = new GroovyTemplateGenerator();
        Map<String, Object> binding = new HashMap<>();
        binding.put("entityName", "User");
        System.out.println(generator.generator("/templates/groovy/repository.tpl", binding));
    }
}
