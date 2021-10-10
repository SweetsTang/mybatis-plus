package com.thomson.mybatisplus;

import com.thomson.mybatisplus.entity.User;
import com.thomson.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testSelectList(){

        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


}
