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

    @DeleteMapping
    public ResponseEntity<String> remove(@RequestBody Folder folder) {
        this.folderService.remove(folder);
        log.info("删除成功");
        return ResponseEntity.ok("删除成功");
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid Folder folder) {
        this.folderService.save(folder);
        log.info("新增成功");
        return ResponseEntity.ok("新增成功");
    }

//    @GetMapping
//    public ResponseEntity queryPage(EasyUIPageRequest pageRequest) {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = ServletUriComponentsBuilder
//                .fromCurrentContextPath()
//                .path("/api/folders")
//                .queryParam("page", pageRequest.getPage() - 1)
//                .queryParam("size", pageRequest.getRows()).toUriString();
//        logger.info(url);
//        ResponseEntity<PagedResources> responseEntity = restTemplate.getForEntity(url, PagedResources.class);
//        PagedResources pagedResources = responseEntity.getBody();
//
//        EasyUIPage easyUIPage = new EasyUIPage();
//        easyUIPage.setRows(pagedResources.getContent());
//        easyUIPage.setTotal(pagedResources.getMetadata().getTotalElements());
//
//        return new ResponseEntity<>(easyUIPage, HttpStatus.OK);
//    }
}
