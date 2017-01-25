package org.mcclone.domain.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author McClone
 */
@Entity
@Table(name = "T_MESSAGE_PUSH")
@Data
@EqualsAndHashCode(callSuper = true)
@EntityListeners({AuditingEntityListener.class})
public class PushedMessage extends AuditGenerator {

    private static final long serialVersionUID = 3866788033319310401L;

    @Column(name = "push_content", nullable = false)
    private String pushContent;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATE_USER_ID")
    private User createUser;
}
