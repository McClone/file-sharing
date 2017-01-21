package org.mcclone.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.mcclone.domain.entity.Folder;
import org.mcclone.domain.repositories.FolderRepository;
import org.mcclone.service.FolderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by mcclone on 17-1-12.
 */
@Service
public class FolderServiceImpl implements FolderService {

    @Resource
    private FolderRepository folderRepository;

    @Resource
    private StandardPasswordEncoder passwordEncoder;

    @Override
    public Page<Folder> findAll(Pageable pageable) {
        return folderRepository.findAll(pageable);
    }

    @Override
    public void save(Folder folder) {
        folder.setPassword(passwordEncoder.encode(folder.getPassword()));
        this.folderRepository.save(folder);
    }

    @Override
    public void remove(String folderId) {
        Folder f = this.folderRepository.findOne(folderId);
        Assert.notNull(f);
        f.setDeleted(1);
        this.folderRepository.save(f);
    }

    @Override
    public boolean validatePassword(String folderId, String password) {
        Folder folder = folderRepository.findOne(folderId);
        if (StringUtils.isNotEmpty(password) && passwordEncoder.matches(password, folder.getPassword())) {
            return true;
        }
        return false;
    }
}
