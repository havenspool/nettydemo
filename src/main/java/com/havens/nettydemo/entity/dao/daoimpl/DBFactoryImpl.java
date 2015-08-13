package com.havens.nettydemo.entity.dao.daoimpl;

import com.havens.nettydemo.entity.dao.DBFactory;
import com.havens.nettydemo.entity.dao.UserDao;

/**
 * Created by havens on 15-8-13.
 */
public class DBFactoryImpl implements DBFactory {
    public void init(){
//        userDao().init();
    }

    public UserDao userDao() {
        return new UserImplDao();
    }
}