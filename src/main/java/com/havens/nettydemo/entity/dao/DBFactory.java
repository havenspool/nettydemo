package com.havens.nettydemo.entity.dao;

/**
 * Created by havens on 15-8-13.
 */
public interface DBFactory {
    void init();
    UserDao userDao();
}
