package org.project.image.service;

import org.project.image.entity.Record;

import java.util.List;

public interface RecordService {

    int insert(Record record);

    int deleteByPrimaryKey(String hid,String uid);

    List<Record> selectByUser(String id);

    Record selectByPrimaryKey(String hid,String uid);
}
