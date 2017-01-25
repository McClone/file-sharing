package org.mcclone.web.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author McClone
 */
@Data
public class FolderView implements Serializable {
    private static final long serialVersionUID = -8266843879266388370L;

    private String id;
    private String folderName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdDate;
    private String createUsername;

}
