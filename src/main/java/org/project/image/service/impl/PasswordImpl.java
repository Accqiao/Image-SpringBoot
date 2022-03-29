package org.project.image.service.impl;

import org.project.image.entity.Password;
import org.project.image.mapper.PasswordMapper;
import org.project.image.service.PasswordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PasswordImpl implements PasswordService {

    @Resource
    private PasswordMapper mapper;

    @Override
    public int insert(Password record) {
        return 0;
    }

    @Override
    public Password selectByPrimaryKey(String uid) {
        return mapper.selectByPrimaryKey(uid);
    }

    @Override
    public int updateByPrimaryKey(Password record) {
        return mapper.updateByPrimaryKey(record);
    }
}
