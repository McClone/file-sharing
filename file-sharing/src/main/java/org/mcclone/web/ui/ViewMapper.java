package org.mcclone.web.ui;

import java.util.Collection;

/**
 * @author McClone
 */
public interface ViewMapper<T, F> {

    F mapping(T t);

    Collection<F> mapping(Collection<T> t);
}
