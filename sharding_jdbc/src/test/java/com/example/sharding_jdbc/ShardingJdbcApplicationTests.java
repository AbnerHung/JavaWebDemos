package com.example.sharding_jdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sharding_jdbc.entity.Course;
import com.example.sharding_jdbc.entity.Udict;
import com.example.sharding_jdbc.entity.User;
import com.example.sharding_jdbc.mapper.CourseMapper;
import com.example.sharding_jdbc.mapper.UdictMapper;
import com.example.sharding_jdbc.mapper.UserMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcApplicationTests {

    //注入Mapper
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UdictMapper udictMapper;
    //=============测试水平分表=============//

    @Test
    public void addCourse(){
        Course course = new Course();
        course.setCname("Java");
        course.setUserId(100L);
        course.setCstatus("Nomal");
        courseMapper.insert(course);
    }

    @Test
    public void addCourses(){
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setCname("Java");
            course.setUserId(100L);
            course.setCstatus("Nomal");
            courseMapper.insert(course);
        }
    }

    @Test
    public void findCourse(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cid",651872896779550721L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }

    //=============测试水平分库=============//


    @Test
    public void addCourseDb(){
        Course course = new Course();
        course.setCname("Javademo");
        //分库根据user_id
        course.setUserId(100L);
        course.setCstatus("Nomal");
        courseMapper.insert(course);
    }
    @Test
    public void findCourseDb(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",100L);
        wrapper.eq("cid",651883586785378304L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }

    //=============测试垂直分库=============//
    @Test
    public void addUser(){
        User user = new User();
        user.setUsername("FuckLucy");
        user.setUstatus("B");
        userMapper.insert(user);
    }
    @Test
    public void findUser(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userid",651895510713499648L);
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    //=============测试公共表=============//
    @Test
    public void addDict(){
        Udict udict = new Udict();
        udict.setUstatus("a");
        udict.setUvalue("已启用");
        udictMapper.insert(udict);
    }

    @Test
    public void deleteDict(){
        QueryWrapper<Udict> wapper = new QueryWrapper<>();
        wapper.eq("dict_id",652072918552936448L);
        udictMapper.delete(wapper);
    }

}
