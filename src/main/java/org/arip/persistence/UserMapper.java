package org.arip.persistence;

import org.arip.domain.User;

import java.util.List;

/**
 * Created by Arip Hidayat on 21/07/2016.
 */
public interface UserMapper {

    List<User> getUsers();
}
