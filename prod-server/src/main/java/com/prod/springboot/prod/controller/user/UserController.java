package com.prod.springboot.prod.controller.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "")
public class UserController {

    @RequestMapping(value = "/")
    public String index() {
        return "login.html";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(@RequestParam(value="userName",required=false) String userName, @RequestParam(value="password",required=false) String password) {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return "login";
        }
        return "success";
    }
}
