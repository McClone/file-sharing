package org.mcclone.web.ui;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author McClone
 */
public class EasyUIGenerator {

    public static Pageable createPageable(EasyUIPageRequest pageRequest) {
        return new PageRequest(pageRequest.getPage() - 1, pageRequest.getRows());
    }

    public static Pageable createPageable(EasyUIPageRequest pageRequest, Sort sort) {
        return new PageRequest(pageRequest.getPage() - 1, pageRequest.getRows(), sort);
    }

    public static Pageable createPageable(EasyUIPageRequest pageRequest, Sort.Direction direction, String... properties) {
        return new PageRequest(pageRequest.getPage() - 1, pageRequest.getRows(), direction, properties);
    }

    public static <T> EasyUIPage<T> createEasyUIPage(Page<T> page) {
        EasyUIPage<T> easyUIPage = new EasyUIPage<>();
        easyUIPage.setRows(page.getContent());
        easyUIPage.setTotal(page.getTotalElements());
        return easyUIPage;
    }

    public static <T, F> EasyUIPage<F> createEasyUIPage(Page<T> page, ViewMapper<T, F> viewMapper) {
        EasyUIPage<F> easyUIPage = new EasyUIPage<>();
        easyUIPage.setRows(viewMapper.mapping(page.getContent()));
        easyUIPage.setTotal(page.getTotalElements());
        return easyUIPage;
    }
}
