package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.dao.impl.UserDaoImpl;
import com.example.domain.PageBean;
import com.example.domain.User;
import com.example.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        //调用DAO完成查询
        return dao.findAll();
    }

    @Override
    public boolean insertInto(User user) {
        return dao.insertInto(user);
    }

    @Override
    public boolean deleteUser(String id) {
        return dao.deleteUser(Integer.parseInt(id));
    }

    @Override
    public boolean updateUser(User user) {
        return dao.updateUser(user);
    }

    @Override
    public User login(User user) {
        return dao.findUserByEmailAndPassword(user.getEmail(),user.getPassword());
    }

    @Override
    public User findUserById(String id) {
        return dao.findUserById(Integer.parseInt(id));
    }

    @Override
    public void deleteUsers(String[] ids) {
        if(ids!=null&&ids.length>0){
            for (String id : ids) {
                dao.deleteUser(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //1.创建空的PageBean对象
        PageBean<User> pb = new PageBean<>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //3.调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<User> list = dao.findByPage(start,rows,condition);
        pb.setList(list);
        //5.计算总页码
        int totalPages = (totalCount % rows) == 0 ? totalCount/rows : totalCount/rows + 1;
        pb.setTotalPages(totalPages);
        return pb;
    }
}
