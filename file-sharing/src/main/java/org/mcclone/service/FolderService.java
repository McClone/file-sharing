package org.mcclone.service;

import org.mcclone.domain.entity.Folder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by mcclone on 17-1-12.
 */
public interface FolderService {

    Page<Folder> findAll(Pageable pageable);

    void save(Folder folder);

    void remove(Folder folder);

}
