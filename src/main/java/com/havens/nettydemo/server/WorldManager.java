package com.havens.nettydemo.server;

import com.havens.nettydemo.Controller.UserController;
import com.havens.nettydemo.db.DBException;
import com.havens.nettydemo.entity.User;
import com.havens.nettydemo.entity.dao.DBFactory;
import com.havens.nettydemo.entity.dao.daoimpl.DBFactoryImpl;

/**
 * Created by havens on 15-8-13.
 */
public class WorldManager {
    private DBFactory dbFactory;
    private static WorldManager god;

    private WorldManager(Server server){
        dbFactory = new DBFactoryImpl();
        dbFactory.init();
    }

    private void buildUpTheWorld() {

    }

    public static WorldManager getInstance(Server server) {
        if (god == null) {
            god = new WorldManager(server);
            god.buildUpTheWorld();
        }
        return god;
    }

    private UserController getUserController(int userId) {
        User user = new User();
        user.id = userId;
        UserController userCtrl = new UserController(user);
        userCtrl.initDAO(dbFactory);
        return userCtrl;
    }

    public DBFactory dbFactory() {
        return this.dbFactory;
    }
}
