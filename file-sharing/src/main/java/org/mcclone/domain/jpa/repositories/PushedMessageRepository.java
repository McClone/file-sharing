package org.mcclone.domain.jpa.repositories;

import org.mcclone.domain.jpa.entity.PushedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author McClone
 */
public interface PushedMessageRepository extends JpaRepository<PushedMessage, String> {



}
