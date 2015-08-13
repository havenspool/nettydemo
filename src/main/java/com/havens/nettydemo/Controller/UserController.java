package com.havens.nettydemo.Controller;

import com.havens.nettydemo.entity.User;
import com.havens.nettydemo.entity.dao.DBFactory;
import com.havens.nettydemo.entity.dao.UserDao;

/**
 * Created by havens on 15-8-13.
 */
public class UserController {
    public User user;
    public UserController(User user) {
        this.user = user;
    }

    private DBFactory dbFactory;

    public void initDAO(DBFactory dbFactory) {
        this.dbFactory = dbFactory;
    }

    private UserDao userDao = null;
    public UserDao userDao() {
        if (userDao == null) {
            userDao = dbFactory.userDao();
        }
        return userDao;
    }
}
