package com.demo.service.impl;

import com.demo.model.UserEntity;
import com.demo.dao.UserDao;
import com.demo.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserEntity getUser(int id) {
        UserEntity userEntity = userDao.getUser(id);
        return userEntity;
    }
}
