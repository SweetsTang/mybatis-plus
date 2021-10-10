package com.thomson.mybatisplus.mapper;

import com.thomson.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class UserMapperTests {

    @Resource
    UserMapper userMapper;

    @Test
    void test(){

        User user = new User();
        user.setUserName("王五");
        user.setUserAge(25);
        user.setUserEmail("wangwu@qq.com");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        int result =userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    void testSelectAllByName(){
        List<User> users = userMapper.selectAllByName("王五");
        users.forEach(System.out::println);
    }

    @Test
    void testFiledFillAge(){
        User user = new User();
        user.setUserName("李四");
        user.setUserAge(24);
        user.setUserEmail("lisi@qq.com");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        int result =userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    void testFiledFill(){
        User user = new User();
        user.setUserName("赵六");
//        user.setUserAge(26);
        user.setUserEmail("zhaoliu@qq.com");
//        user.setCreateTime(LocalDateTime.now());
//        user.setUpdateTime(LocalDateTime.now());

        int result =userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    void testDeleted(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_name","王五");
        int i = userMapper.deleteByMap(map);
    }

    @Test
    void testSelectList(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testInitUser(){

        for (int i=10;i<17;i++){
            User user = new User();
            user.setUserName("王五"+i);
            user.setUserEmail(i+"wangwu@qq.com");
            int result =userMapper.insert(user);
        }
    }
}
