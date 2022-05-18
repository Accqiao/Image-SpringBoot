package org.project.image.service;

import org.project.image.entity.Concern;

import java.util.List;

public interface ConcernService {

    int deleteByPrimaryKey(String uid,String uuid);

    int insert(Concern record);

    List<Concern> selectUserByUser(String uid ,int begin ,int rows);

    int selectConcernConut();

    List<Concern> selectAllUserByUser(String uid);

    Concern selectByPrimaryKey(String uid,String uuid);
}
