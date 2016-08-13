package org.arip.service;

import org.arip.domain.User;

import java.util.List;

/**
 * Created by Arip Hidayat on 21/07/2016.
 */
public interface UserService {

    List<User> getUsers();

    int save(User user);
}
