package org.mcclone.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by mcclone on 17-1-13.
 */
@Entity
@Table(name = "T_USER")
@Data
@EqualsAndHashCode(callSuper = true)
@EntityListeners({AuditingEntityListener.class})
public class User extends AuditGenerator implements Serializable {
    private static final long serialVersionUID = -5962645436589379490L;

    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "REAL_NAME")
    private String realName;

    @Column(name = "deleted")
    private Integer deleted = 0;

}
