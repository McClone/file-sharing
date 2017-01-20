package org.mcclone.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mcclone on 17-1-11.
 */
@Entity
@Table(name = "T_FOLDER")
@Data
@EqualsAndHashCode(callSuper = true)
@EntityListeners({AuditingEntityListener.class})
public class Folder extends AuditGenerator implements Serializable {

    private static final long serialVersionUID = -702960431883202829L;

    @Column(name = "folder_name", nullable = false)
    @NotEmpty(message = "文件夹名称不能为空")
    private String folderName;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATE_USER_ID")
    private User createUser;

    @Column(name = "deleted")
    private Integer deleted = 0;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FileItem> fileItems;

    @Override
    public String toString() {
        return "Folder{" +
                "id='" + id + '\'' +
                ", folderName='" + folderName + '\'' +
                ", createUser='" + createUser + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
