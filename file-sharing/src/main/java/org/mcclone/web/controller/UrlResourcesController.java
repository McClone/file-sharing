package org.mcclone.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.mcclone.service.ResourceService;
import org.mcclone.web.ui.EasyUIGenerator;
import org.mcclone.web.ui.EasyUIPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by mcclone on 17-1-14.
 */
@Slf4j
@RestController
@RequestMapping("url-resources")
public class UrlResourcesController {

    @Resource
    private ResourceService resourceService;

    @GetMapping
    public ResponseEntity findAll(EasyUIPageRequest pageRequest) {
        Page<org.mcclone.domain.jpa.entity.Resource> resources = resourceService.findAll(EasyUIGenerator.createPageable(pageRequest, Sort.Direction.DESC, "patterns", "createdDate"));
        return ResponseEntity.ok(EasyUIGenerator.createEasyUIPage(resources));
    }

    @PostMapping("/mvc")
    public ResponseEntity<String> createMvcUrl() {
        resourceService.saveMvcUrl();
        return ResponseEntity.ok("ok");
    }
}
