package org.project.image.service.impl;

import org.project.image.mapper.HistoryMapper;
import org.project.image.mapper.ImagelikeMapper;
import org.project.image.service.HistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class HistoryImpl implements HistoryService {

    @Resource
    private HistoryMapper mapper;

    @Override
    public void insertOrAddOne(String uid, String hid) {
        mapper.insertOrAddOne(uid, hid);
    }
}
