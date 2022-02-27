package com.hahaen.wxshop.service;

import com.hahaen.wxshop.dao.UserDao;
import com.hahaen.wxshop.generate.User;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
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
            userDao.insertUser(user);
        } catch (DuplicateKeyException e) {
            return userDao.getUserByTel(tel);
        }
        return user;
    }

    /**
     * 根据电话返回用户，如果用户不存在,返回null
     *
     * @param tel
     * @return 返回用户
     */
    public Optional<User> getUserByTel(String tel) {
        return Optional.of(userDao.getUserByTel(tel));
    }
}
