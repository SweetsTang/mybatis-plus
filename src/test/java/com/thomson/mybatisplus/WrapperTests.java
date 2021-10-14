package com.thomson.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.thomson.mybatisplus.entity.User;
import com.thomson.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Thomson
 * @description: wrapper 条件构造器测试类
 * @date 2021/10/14 14:15
 */

@SpringBootTest
public class WrapperTests {

    @Resource
    private UserMapper userMapper;


    /**
     * 组装条件排序
     */
    @Test
    public void test1(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("user_name","王")
                .between("user_age",18,26)
                .isNotNull("user_email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    /**
     * 组装排序条件
     */
    @Test
    public void test2(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("user_age")
                .orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 组装删除条件
     */
    @Test
    public void test3(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .isNull("user_email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("delete return count = " + result);

    }

    /**
     * 条件优先级
     */
    @Test
    public void test4(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("user_name","王")
                .and(i -> i.gt("user_age",18).or().isNull("user_email"));

        User user = new User();
        user.setUserAge(18);
        user.setUserEmail("18@163.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println(result);

    }

    /**
     * 组装select字距
     */
    @Test
    public void test5(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name","user_age");

        //selectMaps()返回Map集合列表，通常配合Select()使用，避免User对象中没有被查询到的列为null
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);

    }

    /**
     * 实现子查询
     */
    @Test
    public void test6(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        // 这种方式容易引发sql注入
//        queryWrapper.inSql("id","select id from user where id < 1446838074783883265");

        queryWrapper.lt("id",3);

        // selectObjs的使用场景，只返回一列
        List<Object> objects = userMapper.selectObjs(queryWrapper);
        objects.forEach(System.out::println);

    }


    /**
     * UpdateWrapper
     */
    @Test
    public void test7(){

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .set("user_age",19)
                .set("user_email","user@qq.com")
                .like("user_name","王")
                .and(i -> i.gt("user_age",17).or().isNull("user_email"));
                // and中的额lambda表达式内的逻辑优先运算

        // 这里必须要创建User对象，否则无法应用自动填充，如果没有自动填充，可以用设置为null
        User user = new User();
        int result = userMapper.update(user, updateWrapper);
        System.out.println(result);

    }

    /**
     *codition 动态组装查询条件
     *
     */
    @Test
    public void test8(){

        String userName = null;
        Integer ageBegin = 20;
        Integer ageEnd = 40;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(userName)){
            queryWrapper.like("user_name","王");
        }
        if(ageBegin != null){
            queryWrapper.ge("user_age",ageBegin);
        }
        if(ageEnd != null){
            queryWrapper.lt("user_age",ageEnd);
        }

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test8Condition(){

        String userName = null;
        Integer ageBegin = 20;
        Integer ageEnd = 40;


        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like(StringUtils.isNotBlank(userName),"user_name",userName)
                .ge(ageBegin != null,"user_age",ageBegin)
                .le(ageEnd != null,"user_age",ageEnd);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    /**
     * lambdaQueryWrapper
     */
    @Test
    public void test9(){

        String userName = null;
        Integer ageBegin = 20;
        Integer ageEnd = 40;

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .like(StringUtils.isNotBlank(userName), User::getUserName, "王")
                .ge(ageBegin != null, User::getUserAge, ageBegin)
                .le(ageEnd != null, User::getUserAge, ageEnd);

        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test10(){

        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper
                .set(User::getUserAge,23)
                .set(User::getUserEmail,"user@lambdaupdate.com")
                .like(User::getUserName,"王")
                .and(i -> i.lt(User::getUserAge,20).or().isNotNull(User::getUserEmail));

        User user = new User();
        int result = userMapper.update(user, lambdaUpdateWrapper);
        System.out.println(result);
    }
}
