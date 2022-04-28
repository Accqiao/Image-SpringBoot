package org.project.image.service.impl;

import org.project.image.entity.Usersimilarity;
import org.project.image.mapper.ImageMapper;
import org.project.image.mapper.UsersimilarityMapper;
import org.project.image.service.SimilarityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class SimilarityImpl implements SimilarityService {

    @Resource
    private UsersimilarityMapper mapper;

    @Override
    public int insert(Usersimilarity record) {
        return mapper.insert(record);
    }

    @Override
    public void insertList(List<Usersimilarity> recordList) {
        for(Usersimilarity si : recordList){
            mapper.insertOrUpdate(si);
        }
    }
}
