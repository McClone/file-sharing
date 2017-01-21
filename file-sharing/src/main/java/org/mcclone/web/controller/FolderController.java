package org.mcclone.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.mcclone.domain.entity.Folder;
import org.mcclone.service.FolderService;
import org.mcclone.web.ui.EasyUIGenerator;
import org.mcclone.web.ui.EasyUIPageRequest;
import org.mcclone.web.ui.ViewMapper;
import org.mcclone.web.view.FolderView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by mcclone on 17-1-11.
 */
@RestController
@RequestMapping("/folders")
@Slf4j
public class FolderController {

    @Resource
    private FolderService folderService;

    @Resource(name = "folderViewMapper")
    private ViewMapper<Folder, FolderView> folderViewMapper;

    @GetMapping
    public ResponseEntity queryPage(EasyUIPageRequest pageRequest) {
        Page<Folder> folderPage = this.folderService.findAll(EasyUIGenerator.createPageable(pageRequest, Sort.Direction.DESC, "createdDate"));
        return new ResponseEntity<>(EasyUIGenerator.createEasyUIPage(folderPage, folderViewMapper), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable String id) {
        this.folderService.remove(id);
        log.info("删除成功");
        return ResponseEntity.ok("删除成功");
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid Folder folder) {
        this.folderService.save(folder);
        log.info("新增成功");
        return ResponseEntity.ok("新增成功");
    }

    @PostMapping("/validate/{id}")
    public ResponseEntity validatePassword(@PathVariable String id, String password) {
        Assert.hasText(password);
        boolean validate = folderService.validatePassword(id, password);
        return validate ? ResponseEntity.ok(true) : ResponseEntity.ok(false);
    }

}
