package com.havens.nettydemo.service;

import com.havens.nettydemo.Service;
import com.havens.nettydemo.db.DBObjectDAO;
import com.havens.nettydemo.entity.User;
import com.havens.nettydemo.entity.dao.UserDao;
import com.havens.nettydemo.entity.dao.daoimpl.UserImplDao;
import com.havens.nettydemo.message.Message;
import com.havens.nettydemo.message.MessageHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by havens on 15-8-11.
 */
public class LoginService extends Service {
    @Override
    public void filter(Map map) throws Exception {
        String name=(String)map.get("name");
        UserDao dao=new UserImplDao();
        User user=null;
        if(name!=""){
            user= dao.getUser(name);
        }
        if(user==null){
            User user2= new User();
            user2.name=name;
            user2.pwd=(String)map.get("pwd");
            dao.insert(user2);

            user=dao.getUser(name);
        }
        write(MessageHelper.login(user));
    }
}
