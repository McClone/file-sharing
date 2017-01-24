package org.mcclone.domain.jpa.repositories;

import org.mcclone.domain.jpa.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mcclone on 17-1-14.
 */
public interface ResourceRepository extends JpaRepository<Resource, String> {

    long deleteAllByCreateType(String createType);


}
