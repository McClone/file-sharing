package org.mcclone.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.mcclone.security.SecurityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mcclone on 17-1-12.
 */
@Controller
@Slf4j
public class HtmlMappingController {

    @RequestMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("/login");
    }

    @GetMapping("/index")
    public String index(Model model) {
        Object principal = SecurityUtils.getPrincipal();
        if (principal != null && principal instanceof UserDetails) {
            model.addAttribute("username", ((UserDetails) principal).getUsername());
        }
        return "index";
    }

}
