package org.arip.service.impl;

import org.arip.persistence.UserMapper;
import org.arip.domain.User;
import org.arip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Arip Hidayat on 21/07/2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public int save(User user) {
        return userMapper.insertUser(user);
    }
}
