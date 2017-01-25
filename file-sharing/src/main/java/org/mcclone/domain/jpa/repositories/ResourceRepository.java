package org.mcclone.domain.jpa.repositories;

import org.mcclone.domain.jpa.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author McClone
 */
public interface ResourceRepository extends JpaRepository<Resource, String> {

    long deleteAllByCreateType(String createType);


}
