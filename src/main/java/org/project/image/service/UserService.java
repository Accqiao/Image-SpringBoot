package org.project.image.service;


import org.project.image.entity.User;

import java.util.List;

public interface UserService {

    int insert(User record);

    User selectByPrimaryKey(String uid);


    int updateByPrimaryKey(User record);

    int updateByPrimaryKeySelective(User record);

    List<User> selectUserbyPage(int page,int rows);

    int selectUserCount();



}
