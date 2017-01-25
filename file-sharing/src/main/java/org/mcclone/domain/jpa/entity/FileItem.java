package org.mcclone.domain.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author McClone
 */
@Entity
@Table(name = "T_FILE_ITEM")
@Data
@EqualsAndHashCode(callSuper = true)
@EntityListeners({AuditingEntityListener.class})
public class FileItem extends IdGenerator implements Serializable {

    private static final long serialVersionUID = -7756832990329985689L;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_path")
    @NotEmpty
    private String filePath;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATE_USER_ID")
    private User createUser;

    @Column(name = "deleted")
    private Integer deleted = 0;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    private Folder folder;

}
