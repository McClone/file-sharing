package org.mcclone.domain.jpa.repositories;

import org.mcclone.domain.jpa.entity.PushedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by McClone on 2017/1/23.
 */
public interface PushedMessageRepository extends JpaRepository<PushedMessage, String> {



}
