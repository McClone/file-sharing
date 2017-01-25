package org.mcclone.web.ui;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author McClone
 */
public abstract class SimpleViewMapper<T, F> implements ViewMapper<T, F> {

    @Override
    public Collection<F> mapping(Collection<T> t) {
        Collection<F> result = new ArrayList<F>();
        for (T item : t) {
            result.add(this.mapping(item));
        }
        return result;
    }
}
