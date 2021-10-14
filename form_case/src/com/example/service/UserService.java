package com.example.service;

import com.example.domain.PageBean;
import com.example.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */

public interface UserService{
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();
    public boolean insertInto(User user);
    public boolean deleteUser(String id);
    public boolean updateUser(User user);
    public User login(User user);
    public User findUserById(String id);
    public void deleteUsers(String[] ids);
    public PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
