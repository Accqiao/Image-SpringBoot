package org.project.image.service;

import org.project.image.entity.Userrecommend;
import org.project.image.entity.Usersimilarity;

import java.util.List;

public interface RecommendService {

    void deleteAll();

    void insertList(List<Userrecommend> recordList);

    List<Userrecommend> selectRecommendByUid(String uid,int begin,int num);

}
