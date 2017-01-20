package org.mcclone.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.mcclone.domain.entity.FileItem;
import org.mcclone.domain.entity.Folder;
import org.mcclone.domain.repositories.FileItemRepository;
import org.mcclone.domain.repositories.FolderRepository;
import org.mcclone.web.ui.EasyUIGenerator;
import org.mcclone.web.ui.EasyUIPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by mcclone on 17-1-12.
 */
@RestController
@RequestMapping("/fileItems")
@Slf4j
public class FileItemController {

    private static final String BASE_UPLOAD_PATH = "/home/mcclone/tmp/upload/";

    @Resource
    private FileItemRepository fileItemRepository;

    @Resource
    private FolderRepository folderRepository;

    @Resource
    private StandardPasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<String> saveFile(@RequestParam MultipartFile file, FileItem fileItem) throws IOException {
        file.transferTo(new File(BASE_UPLOAD_PATH + file.getOriginalFilename()));
        fileItem.setFilePath(BASE_UPLOAD_PATH + file.getOriginalFilename());
        fileItemRepository.save(fileItem);
        return ResponseEntity.ok("保存成功");
    }

    @GetMapping
    public ResponseEntity findALl(EasyUIPageRequest pageRequest, String folderId) {
        Page<FileItem> fileItemPage = fileItemRepository.findAll(EasyUIGenerator.createPageable(pageRequest), folderId);
        return ResponseEntity.ok(EasyUIGenerator.createEasyUIPage(fileItemPage));
    }

    @RequestMapping("/view")
    public ModelAndView fileItemView(String folderId, String password) {
        ModelAndView modelAndView = new ModelAndView();
        Folder folder = folderRepository.findOne(folderId);
        Assert.notNull(folder);
        if (StringUtils.isNotEmpty(password) && passwordEncoder.matches(password, folder.getPassword())) {
            modelAndView.setViewName("/file_item_dialog");
        } else {
            if (StringUtils.isNotEmpty(password)) {
                modelAndView.addObject("message", "密码错误");
            }
            modelAndView.setViewName("/fold_password");
        }
        modelAndView.addObject("folderId", folderId);
        return modelAndView;
    }

    @GetMapping("/download")
    public void downloadFile(HttpServletResponse response, String fileItemId) throws IOException {
        FileItem fileItem = fileItemRepository.findOne(fileItemId);
        String fileName = FilenameUtils.getName(fileItem.getFilePath());

        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        IOUtils.copy(FileUtils.openInputStream(FileUtils.getFile(fileItem.getFilePath())), response.getOutputStream());
    }

}
