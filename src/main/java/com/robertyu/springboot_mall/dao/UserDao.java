package com.robertyu.springboot_mall.dao;

import com.robertyu.springboot_mall.dto.UserRegisterRequest;
import com.robertyu.springboot_mall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
