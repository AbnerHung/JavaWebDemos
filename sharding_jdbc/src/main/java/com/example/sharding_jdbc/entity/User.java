package com.example.sharding_jdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user") //指定对应的表
public class User {
    private Long userid;
    private String username;
    private String ustatus;
}
