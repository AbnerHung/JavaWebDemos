package com.example.sharding_jdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_u_dict")
public class Udict {
    private Long dictId;
    private String ustatus;
    private String uvalue;
}
