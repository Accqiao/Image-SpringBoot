package org.project.image.service;


import org.project.image.entity.User;

import java.util.List;

public interface UserService {

    int insert(User record);

    User selectByPrimaryKey(String uid);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}
