package org.mcclone.web.ui;

import lombok.Data;

import java.util.Collection;

/**
 * Created by mcclone on 17-1-11.
 */
@Data
public class EasyUIPage<T> {

    private Collection<T> rows;

    private long total;

}
