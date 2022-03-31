package org.project.image.service.impl;

import org.project.image.entity.Record;
import org.project.image.mapper.ImagetagsMapper;
import org.project.image.mapper.RecordMapper;
import org.project.image.service.RecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecoedImpl implements RecordService {


    @Resource
    private RecordMapper mapper;

    @Override
    public int insert(Record record) {
        return mapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(String hid, String uid) {
        return mapper.deleteByPrimaryKey(hid, uid);
    }

    @Override
    public List<Record> selectByUser(String id) {
        return mapper.selectByUser(id);
    }

    @Override
    public Record selectByPrimaryKey(String hid, String uid) {
        return mapper.selectByPrimaryKey(hid, uid);
    }
}
