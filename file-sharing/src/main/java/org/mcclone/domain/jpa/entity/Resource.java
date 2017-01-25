package org.mcclone.domain.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author McClone
 */
@Entity
@Table(name = "T_RESOURCE")
@Data
@EqualsAndHashCode(callSuper = true)
@EntityListeners({AuditingEntityListener.class})
public class Resource extends AuditGenerator implements Serializable {
    private static final long serialVersionUID = 2993426733251113433L;

    public static final String MVC_URL_INIT = "mvc-url-init";

    @Column(name = "patterns", nullable = false)
    private String patterns;

    @Column(name = "methods")
    private String methods;

    @Column(name = "description")
    private String description;

    /**
     * mvc-url-init
     * user-create
     */
    @Column(name = "create_type")
    private String createType;

    @Column(name = "media_type")
    private String mediaType;

}
