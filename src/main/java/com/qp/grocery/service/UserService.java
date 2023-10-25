package com.qp.grocery.service;

import com.qp.grocery.model.User;

import java.util.Optional;

public interface UserService {

    public User saveUser(User user);

    public User getUser(String userName);

    public void removeUsers();
}
