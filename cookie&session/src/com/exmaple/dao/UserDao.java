package com.exmaple.dao;

import com.exmaple.domain.User;
import com.exmaple.utils.JdbcUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 操作数据库中User表的类
 */

public class UserDao {


    //声明JDBCTemplate对象公用
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    /**
     * 登录方法
     * @param loginUser 只有用户名和密码
     * @return user 有全部信息
     */
    public User login(User loginUser){
        try {
            String sql = "select * from login_case.user where username = ? and password = ?;";
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(),
                    loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();//应当在此记录日志
            return null;
        }
    }
}
