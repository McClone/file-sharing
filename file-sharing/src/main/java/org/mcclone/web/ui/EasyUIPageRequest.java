package org.mcclone.web.ui;

import lombok.Data;

/**
 * @author McClone
 */
@Data
public class EasyUIPageRequest {

    private static final long serialVersionUID = 3283880685367955067L;

    private int page = 1;

    private int rows = 10;


}
