package org.mcclone.domain.repositories;

import org.mcclone.domain.entity.Folder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by mcclone on 17-1-11.
 */
public interface FolderRepository extends JpaRepository<Folder, String> {

    @Query("select u from #{#entityName} u where u.deleted = 0 or u.deleted is null ")
    Page<Folder> findAll(Pageable pageable);


}
