package com.thomson.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @author Thomson
 * @description: product商品实体类
 * @date 2021/10/12 8:35
 */

@TableName(value = "Product")
@Data
public class Product {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "price")
    private Integer price;

    @Version
    private Integer version;
}