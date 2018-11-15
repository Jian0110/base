package com.lijian.service.impl;


import com.lijian.entity.User;
import com.lijian.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger log = LogManager.getLogger(UserService.class);

    @Autowired
    private  UserMapper userMapper;

    public List<User> listUsers(){
        log.info("执行listUsers()方法");
        return userMapper.listUsers();
    }

    public User getUserById(String userId){
        return userMapper.getUserById(userId);
    }
}
