package org.mcclone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mcclone on 17-1-12.
 */
@Controller
public class LoginController {

    @GetMapping("login")
    public ModelAndView loginPage() {
        return new ModelAndView("/login");
    }

}
