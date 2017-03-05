package com.github.melpis.web;

import com.github.melpis.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

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
    public String joinProcess() {

        return "/login";
    }
}
