package com.github.melpis.web;

import com.github.melpis.domain.User;
import com.github.melpis.service.UserService;
import com.github.melpis.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginProcess(@RequestBody User user) {

        return "/board/list";
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joinForm() {

        return "/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String joinProcess(@ModelAttribute(name = "user") User user) {
        if (userService.save(user)) {
            return "/login";
        }

        return "/join";
    }

}
