package com.anand.dailyaccount.services;

import android.content.ContentValues;

import com.anand.dailyaccount.dao.UserDao;
import com.anand.dailyaccount.entity.User;

/**
 * Created by Anand Jakhaniya on 08-10-2017.
 * @author Anand Jakhaniya
 */

public class UserService {

    private UserDao userDao = null;

    public UserService(){
        userDao = new UserDao();
    }

    public void addUser(User user) {
       userDao.addUser(user);
    }

    public void update(User user) {
       userDao.update(user);
    }

    public User getUser(String email) {
        return userDao.getUser(email);
    }
}
