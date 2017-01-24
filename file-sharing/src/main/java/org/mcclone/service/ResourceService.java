package org.mcclone.service;

import org.mcclone.domain.jpa.entity.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by mcclone on 17-1-14.
 */
public interface ResourceService {

    void saveMvcUrl();

    Page<Resource> findAll(Pageable pageable);
}
