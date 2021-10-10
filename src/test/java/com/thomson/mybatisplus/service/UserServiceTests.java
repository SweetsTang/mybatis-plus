package com.thomson.mybatisplus.service;

import com.thomson.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class UserServiceTests {

    @Resource
    private UserService userService;

    @Test
    void testCount(){
        int count = userService.count();
        System.out.println("总记录数："+count);
    }

    @Test
    void testListAllByName(){
        List<User> users = userService.selectAllByName("王五");
        users.forEach(user -> System.out.println(user));
        users.forEach(System.out::println);
    }
}
