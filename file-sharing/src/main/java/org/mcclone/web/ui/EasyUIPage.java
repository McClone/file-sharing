package org.mcclone.web.ui;

import lombok.Data;

import java.util.Collection;

/**
 * @author McClone
 */
@Data
public class EasyUIPage<T> {

    private Collection<T> rows;

    private long total;

}
