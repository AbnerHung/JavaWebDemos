package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.domain.User;
import com.example.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库
        //1.定义sql
        String sql = "select * from form_case.user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public boolean insertInto(User user) {
        String sql = "insert into form_case.user(`id`,`name`,`gender`,`age`,`address`,`qq`,`email`) values (null,?,?,?,?,?,?);";
        return template.update(sql, user.getName(),user.getGender(), user.getAge(), user.getAddress(), user.getQq(),user.getEmail())>0;
    }

    @Override
    public boolean deleteUser(Integer id) {
        String sql = "delete from form_case.user where `id`= ?;";
        return template.update(sql,id)>0;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "update form_case.user set gender=?, age=?,address=?,qq=?,email=? where id=?;";
        return template.update(sql,user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId())>0;
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        try{
            String sql = "select * from form_case.user where email=? and password=?;";

            User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),email,password);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();//应当在此记录日志
            return null;
        }
    }

    @Override
    public User findUserById(int id) {
        try{
            String sql = "select * from form_case.user where id=?;";

            User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();//应当在此记录日志
            return null;
        }
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //定义初始化模板
        String sql = "select count(*) from form_case.user where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map
        Set<String> keySet = condition.keySet();
        //定义参数集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);
        return template.queryForObject(sb.toString(), Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from form_case.user where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map
        Set<String> keySet = condition.keySet();
        //定义参数集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");
            }
        }
        sb.append(" limit ?, ?;");

        params.add(start);
        params.add(rows);
        System.out.println(sb.toString());
        System.out.println(params);
        sql=sb.toString();

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }
}
