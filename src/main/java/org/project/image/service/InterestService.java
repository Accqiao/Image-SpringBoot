package org.project.image.service;

import org.project.image.entity.Interest;

import java.util.List;

public interface InterestService {

    int deleteByPrimaryKey(String id);

    int insert(Interest record);

    List<Interest> selectByUid(String uid);
}
