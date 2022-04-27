package org.project.image.service.impl;

import org.project.image.entity.Interest;
import org.project.image.mapper.InterestMapper;
import org.project.image.mapper.TagsMapper;
import org.project.image.service.InterestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InterestImpl implements InterestService {
    @Resource
    private InterestMapper mapper;


    @Override
    public int deleteByPrimaryKey(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Interest record) {
        return mapper.insert(record);
    }

    @Override
    public List<Interest> selectByUid(String id) {
        return mapper.selectByUid(id);
    }
}
