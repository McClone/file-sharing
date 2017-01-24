package org.mcclone.domain.jpa.repositories;

import org.mcclone.domain.jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by mcclone on 17-1-13.
 */
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from #{#entityName} u where u.deleted = 0 or u.deleted is null ")
    @Override
    Page<User> findAll(Pageable pageable);

    User findByUsername(String userId);

}
