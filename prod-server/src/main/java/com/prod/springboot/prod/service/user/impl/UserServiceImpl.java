package com.prod.springboot.prod.service.user.impl;

import com.prod.springboot.prod.dao.UserDao;
import com.prod.springboot.prod.entity.User;
import com.prod.springboot.prod.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User findUser(String name, String password) {
        return userDao.findUser(name,password);
    }
}
