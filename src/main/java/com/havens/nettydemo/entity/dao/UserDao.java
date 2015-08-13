package com.havens.nettydemo.entity.dao;

import com.havens.nettydemo.entity.User;

/**
 * Created by havens on 15-8-13.
 */
public interface UserDao {
    User getUser(int id);
    User getUser(String name);
    void insert(User user);
    void update(User user);
    void delete(User user);
    void deleteById(int id);
}
