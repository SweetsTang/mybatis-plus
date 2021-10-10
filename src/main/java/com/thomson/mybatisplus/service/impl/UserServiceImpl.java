package com.thomson.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thomson.mybatisplus.entity.User;
import com.thomson.mybatisplus.mapper.UserMapper;
import com.thomson.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public List<User> selectAllByName(String name) {
        return baseMapper.selectAllByName(name);
    }
}
