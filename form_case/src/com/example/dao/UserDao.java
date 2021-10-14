package com.example.dao;

import com.example.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的DAO
 */
public interface UserDao {
    public List<User> findAll();
    public boolean insertInto(User user);
    public boolean deleteUser(Integer id);
    public boolean updateUser(User user);
    public User findUserByEmailAndPassword(String email,String password);
    public User findUserById(int id);
    public int findTotalCount(Map<String, String[]> condition);
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
