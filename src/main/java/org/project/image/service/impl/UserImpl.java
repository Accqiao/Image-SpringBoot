package org.project.image.service.impl;


import org.project.image.entity.User;
import org.project.image.mapper.UserMapper;
import org.project.image.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserImpl implements UserService {

    @Resource
    private UserMapper mapper;


    @Override
    public int insert(User record) {
        return mapper.insert(record);
    }

    @Override
    public User selectByPrimaryKey(String uid) {
        return mapper.selectByPrimaryKey(uid);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<User> selectUserbyPage(int page, int rows) {
        int begin = (page-1) * rows ;
        return mapper.selectUserbyPage(begin, rows);
    }

    @Override
    public int selectUserCount() {
        return mapper.selectUserCount();
    }
}
