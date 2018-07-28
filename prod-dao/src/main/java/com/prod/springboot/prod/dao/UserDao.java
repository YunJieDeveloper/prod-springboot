package com.prod.springboot.prod.dao;

import com.google.common.collect.Maps;
import com.prod.springboot.prod.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import  com.prod.springboot.prod.utils.JdbcUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Repository
public class UserDao {
    @Resource(name = "prodTemplate")
    private JdbcTemplate prodTemplate;
    private HashMap<String,String> entityToDbMap = Maps.newHashMap();

    @PostConstruct
    public void init() {
        entityToDbMap.put("id", "id");
        entityToDbMap.put("name", "name");
        entityToDbMap.put("password", "password");
        entityToDbMap.put("note", "note");
    }

    public User findUser(String name,String password){

        String sql="SELECT * FROM "+ getTableName()+" WHERE name=? AND password=?";
        Map<String, Object> objectMap = null;
        try {
            objectMap = prodTemplate.queryForMap(sql, name, password);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        User user = JdbcUtils.transferDbResultToEntity(objectMap, entityToDbMap, User.class);
        return user;
    }


    private String getTableName(){
        return "t_user";
    }
}
