package com.hahaen.wxshop.service;

import com.hahaen.wxshop.UserDao;
import com.hahaen.wxshop.generate.User;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUserIfNotExist(String tel) {
        User user = new User();
        user.setTel(tel);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        try {
            userDao.insertUser(new User());
        } catch (PersistenceException e) {
            return userDao.getUserByTel(tel);
        }
        return user;
    }
}
