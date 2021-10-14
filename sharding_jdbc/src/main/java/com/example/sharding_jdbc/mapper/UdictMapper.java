package com.example.sharding_jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sharding_jdbc.entity.Udict;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Repository
@MapperScan("com.example.sharding_jdbc.mapper")
public interface UdictMapper extends BaseMapper<Udict>{

}
