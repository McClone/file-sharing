package org.mcclone.web.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author McClone
 */
@Data
public class PushedMessageView {

    private String pushContent;
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdDate;
    private String createUsername;
}
