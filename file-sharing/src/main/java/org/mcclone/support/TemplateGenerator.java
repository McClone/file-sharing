package org.mcclone.support;

import java.util.Map;

/**
 * Created by mcclone on 17-1-19.
 */
public interface TemplateGenerator<T> {

    T generator(String templatePath, Map data) throws Exception;
}
