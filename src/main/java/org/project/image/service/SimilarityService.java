package org.project.image.service;

import org.project.image.entity.Usersimilarity;

import java.util.List;

public interface SimilarityService {

    int insert(Usersimilarity record);

    void insertList(List<Usersimilarity> recordList);
}
