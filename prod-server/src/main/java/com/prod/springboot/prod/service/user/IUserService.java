package com.prod.springboot.prod.service.user;

import com.prod.springboot.prod.entity.User;

public interface IUserService {
    User findUser(String name, String password);
}
