package com.nsgacademy.crudapp.dao;

import com.nsgacademy.crudapp.model.User;

public interface UserDAO {

    void register(User user);

    User login(String username, String password);

    boolean exists(String username);
}
