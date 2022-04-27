package org.project.image.service.impl;

import org.project.image.entity.Tags;
import org.project.image.entity.User;
import org.project.image.mapper.TagsMapper;
import org.project.image.service.TagsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagsImpl implements TagsService {

    @Resource
    private TagsMapper mapper;

    @Override
    public List<Tags> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<Tags> selectAllByLevelTags(String level) {
        return mapper.selectAllByLevelTags(level);
    }

}
