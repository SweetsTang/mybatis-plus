package com.thomson.mybatisplus.mapper;

import com.thomson.mybatisplus.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Thomson
 * @description: ProductMapper测试类
 * @date 2021/10/12 8:41
 */
@SpringBootTest
public class ProductMapperTests {

    @Resource
    private ProductMapper productMapper;

    @Test
    public void testConcurrentUpdate(){

        // 1、小李
        Product p1 = productMapper.selectById(1L);
        // 2、小王
        Product p2 = productMapper.selectById(1L);
        // 3、小李将价格加了50元，存入数据库
        p1.setPrice(p1.getPrice()+50);
        int result1 = productMapper.updateById(p1);
        System.out.println("小李修改后结果："+ result1);
        // 4、小王将价格减了30，存入数据库
        p2.setPrice(p2.getPrice()-30);
        int result2 = productMapper.updateById(p2);
        System.out.println("小王修改后结果："+ result2);
        // 最后结果
        Product p3 = productMapper.selectById(1L);
        System.out.println("最后结果：" + p3.getPrice());

    }

    @Test
    public void testConcurrentUpdate2(){

        // 1、小李
        Product p1 = productMapper.selectById(1L);
        // 2、小王
        Product p2 = productMapper.selectById(1L);
        // 3、小李将价格加了50元，存入数据库
        p1.setPrice(p1.getPrice()+50);
//        int result1 = productMapper.updateById(p1);
//        System.out.println("小李修改后结果："+ result1);
        // 4、小王将价格减了30，存入数据库
        p2.setPrice(p2.getPrice()-30);
        int result2 = productMapper.updateById(p2);
//        if(result2 == 0){
//            Product p4 = productMapper.selectById(1L);
//            p2.setPrice(p4.getPrice()-30);
//        }
        System.out.println("小王修改后结果："+ result2);
        // 最后结果
        Product p3 = productMapper.selectById(1L);
        System.out.println("最后结果：" + p3.getPrice());
    }

}
