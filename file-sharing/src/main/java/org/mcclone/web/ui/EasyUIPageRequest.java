package org.mcclone.web.ui;

import lombok.Data;

/**
 * Created by mcclone on 17-1-11.
 */
@Data
public class EasyUIPageRequest {

    private static final long serialVersionUID = 3283880685367955067L;

    private int page = 1;

    private int rows = 10;


}
