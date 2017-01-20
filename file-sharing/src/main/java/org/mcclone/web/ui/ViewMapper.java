package org.mcclone.web.ui;

import java.util.Collection;

/**
 * Created by mcclone on 17-1-14.
 */
public interface ViewMapper<T, F> {

    F mapping(T t);

    Collection<F> mapping(Collection<T> t);
}
