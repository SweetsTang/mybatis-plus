package com.thomson.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thomson.mybatisplus.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> selectAllByName(String name);
}
