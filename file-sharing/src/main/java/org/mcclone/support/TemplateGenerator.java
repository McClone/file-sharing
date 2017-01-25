package org.mcclone.support;

import java.util.Map;

/**
 * @author McClone
 */
public interface TemplateGenerator<T> {

    T generator(String templatePath, Map data) throws Exception;
}
