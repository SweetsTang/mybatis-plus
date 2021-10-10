package com.thomson.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thomson.mybatisplus.entity.User;
import com.thomson.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class InterceptorTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void testSelectPage(){
        Page<User> pageParam = new Page<>(1,5);
        userMapper.selectPage(pageParam,null);
        List<User> users = pageParam.getRecords();
        users.forEach(System.out::println);
        System.out.println(pageParam.getCurrent());
        System.out.println(pageParam.getTotal());
        System.out.println(pageParam.hasNext());
        System.out.println(pageParam.hasPrevious());

    }

}
