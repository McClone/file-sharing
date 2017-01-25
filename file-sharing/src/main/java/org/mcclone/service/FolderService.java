package org.mcclone.service;

import org.mcclone.domain.jpa.entity.Folder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author McClone
 */
public interface FolderService {

    Page<Folder> findAll(Pageable pageable);

    void save(Folder folder);

    void remove(String folderId);

    boolean validatePassword(String folderId, String password);
}
