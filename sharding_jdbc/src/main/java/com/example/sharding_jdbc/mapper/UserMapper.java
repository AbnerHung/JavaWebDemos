package com.example.sharding_jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sharding_jdbc.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;


@Repository
@MapperScan("com.example.sharding_jdbc.mapper")
public interface UserMapper extends BaseMapper<User> {
}
