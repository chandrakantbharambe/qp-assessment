package com.qp.grocery.service.impl;

import com.qp.grocery.model.User;
import com.qp.grocery.repository.UserRepository;
import com.qp.grocery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public User getUser(String userName) {
        Optional<User> optionalUser = repository.findById(userName);

        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User " + userName + " not found");
        }
        return optionalUser.get();
    }

    @Override
    public void removeUsers() {
        repository.deleteAll();
    }


}
