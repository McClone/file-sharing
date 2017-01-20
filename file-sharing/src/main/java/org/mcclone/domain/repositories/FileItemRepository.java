package org.mcclone.domain.repositories;

import org.mcclone.domain.entity.FileItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by mcclone on 17-1-11.
 */
public interface FileItemRepository extends JpaRepository<FileItem, String> {

    @Query("select u from #{#entityName} u where u.folder.id = ?1 ")
    Page<FileItem> findAll(Pageable pageable, String folderId);

}
