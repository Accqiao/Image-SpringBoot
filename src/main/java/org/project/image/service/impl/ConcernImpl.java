package org.project.image.service.impl;

import org.project.image.entity.Concern;
import org.project.image.mapper.ConcernMapper;
import org.project.image.service.ConcernService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConcernImpl implements ConcernService {
    @Resource
    private ConcernMapper mapper;

    @Override
    public int deleteByPrimaryKey(String uid, String uuid) {
        return mapper.deleteByPrimaryKey(uid, uuid);
    }

    @Override
    public int insert(Concern record) {
        return mapper.insert(record);
    }

    @Override
    public List<Concern> selectUserByUser(String uid, int begin, int rows) {
        return mapper.selectUserByUser(uid, begin, rows);
    }

    @Override
    public int selectConcernConut() {
        return mapper.selectConcernConut();
    }

    @Override
    public List<Concern> selectAllUserByUser(String uid) {
        return mapper.selectAllUserByUser(uid);
    }

    @Override
    public Concern selectByPrimaryKey(String uid, String uuid) {
        return mapper.selectByPrimaryKey(uid, uuid);
    }
}
