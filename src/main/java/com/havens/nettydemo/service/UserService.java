package com.havens.nettydemo.service;

import com.havens.nettydemo.Controller.UserController;
import com.havens.nettydemo.Service;
import com.havens.nettydemo.server.Server;
import com.havens.nettydemo.server.WorldManager;

import java.util.Map;

/**
 * Created by havens on 15-8-13.
 */
public abstract class UserService extends Service{
    public UserController userCtrl;
    protected WorldManager worldManager;

    @Override
    public void create(Server server) throws Exception {
        super.create(server);
        worldManager = WorldManager.getInstance(server);
    }

    public boolean beforeFilter(final Map map) throws Exception {
        return true;
    }

}
//userCtrl = worldManager.onlineUser().getUnchecked(userId);