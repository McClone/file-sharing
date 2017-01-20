package org.mcclone.web.controller;

import org.mcclone.utils.SecurityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mcclone on 17-1-11.
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        UserDetails userDetails = SecurityUtils.getUserDetails();
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        return "/index";
    }

    @GetMapping("/nav/{viewName}")
    public ModelAndView navView(@PathVariable String viewName) {
        return new ModelAndView("/" + viewName);
    }
}
