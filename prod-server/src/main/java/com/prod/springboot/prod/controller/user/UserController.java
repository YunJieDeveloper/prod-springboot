package com.prod.springboot.prod.controller.user;

import com.prod.springboot.prod.entity.User;
import com.prod.springboot.prod.service.user.IUserService;
import com.prod.springboot.prod.service.user.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "")
public class UserController {
    @Resource(name="userServiceImpl")
    private IUserService userService;

    @RequestMapping(value = "/")
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(@RequestParam(value="userName",required=false) String userName, @RequestParam(value="password",required=false) String password) {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return "login";
        }else{
            User user = userService.findUser(userName, password);
            return "success";
        }

    }
}
