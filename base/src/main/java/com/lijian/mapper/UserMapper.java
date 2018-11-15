package com.lijian.mapper;

import com.lijian.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> listUsers();

    User getUserById(String id);
}
