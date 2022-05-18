package org.project.image.service.impl;

import org.project.image.entity.History;
import org.project.image.mapper.HistoryMapper;
import org.project.image.service.HistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HistoryImpl implements HistoryService {

    @Resource
    private HistoryMapper mapper;

    @Override
    public void insertOrAddOne(String uid, String hid) {
        mapper.insertOrAddOne(uid, hid);
    }

    @Override
    public List<History> selectByUid(String uid) {
        return mapper.selectByUid(uid);
    }

    @Override
    public History selectByPrimaryKey(String uid, String hid) {
        return mapper.selectByPrimaryKey(uid, hid);
    }

    @Override
    public List<History> selectByUidByTime(String uid) {
        return mapper.selectByUidByTime(uid);
    }

    @Override
    public List<History> selectByUidByTimeByLimit(String uid, int begin, int rows) {
        return mapper.selectByUidByTimeByLimit(uid, begin, rows);
    }
}
