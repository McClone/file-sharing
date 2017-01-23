package org.mcclone.web.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by McClone on 2017/1/23.
 */
@Data
public class PushedMessageView {

    private String pushContent;
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdDate;
    private String createUsername;
}
