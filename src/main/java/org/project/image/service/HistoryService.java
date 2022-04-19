package org.project.image.service;

import org.project.image.entity.History;

import java.util.List;

public interface HistoryService {

    void insertOrAddOne(String uid ,String hid);

    List<History> selectByUid(String uid);

    History selectByPrimaryKey(String uid,String hid);

    List<History> selectByUidByTime(String uid);
}
