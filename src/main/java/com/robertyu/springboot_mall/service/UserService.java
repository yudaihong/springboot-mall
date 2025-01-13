package com.robertyu.springboot_mall.service;

import com.robertyu.springboot_mall.dto.UserRegisterRequest;
import com.robertyu.springboot_mall.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);
}
