package org.mcclone.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.mcclone.domain.jpa.entity.FileItem;
import org.mcclone.domain.jpa.repositories.FileItemRepository;
import org.mcclone.web.ui.EasyUIGenerator;
import org.mcclone.web.ui.EasyUIPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author McClone
 */
@RestController
@RequestMapping("/fileItems")
@Slf4j
public class FileItemController {

    private static final String BASE_UPLOAD_PATH = "/home/mcclone/tmp/upload/";

    @Resource
    private FileItemRepository fileItemRepository;

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

    @GetMapping("/download")
    public void downloadFile(HttpServletResponse response, String fileItemId) throws IOException {
        FileItem fileItem = fileItemRepository.findOne(fileItemId);
        String fileName = FilenameUtils.getName(fileItem.getFilePath());

        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        IOUtils.copy(FileUtils.openInputStream(FileUtils.getFile(fileItem.getFilePath())), response.getOutputStream());
    }

}
