package org.mcclone.web.mapper;

import org.mcclone.domain.jpa.entity.Folder;
import org.mcclone.web.ui.SimpleViewMapper;
import org.mcclone.web.view.FolderView;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Created by mcclone on 17-1-14.
 */
@Component("folderViewMapper")
public class FolderViewMapper extends SimpleViewMapper<Folder, FolderView> {

    @Override
    public FolderView mapping(Folder folder) {
        FolderView folderView = new FolderView();
        BeanUtils.copyProperties(folder, folderView, "createUser", "fileItems");
        if (folder.getCreateUser() != null) {
            folderView.setCreateUsername(folder.getCreateUser().getUsername());
        }
        return folderView;
    }
}
