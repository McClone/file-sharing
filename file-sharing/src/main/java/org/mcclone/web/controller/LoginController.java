package org.mcclone.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mcclone on 17-1-12.
 */
@RestController
@Slf4j
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ModelAndView loginPage() {
        return new ModelAndView("/login");
    }

}
