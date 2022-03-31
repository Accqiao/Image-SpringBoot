package org.project.image.service.impl;

import org.project.image.entity.Image;
import org.project.image.entity.Imagetags;
import org.project.image.mapper.ImageMapper;
import org.project.image.mapper.TagsMapper;
import org.project.image.service.ImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImageImpl implements ImageService {
    @Resource
    private ImageMapper mapper;


    @Override
    public Image selectByPrimaryKey(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(Image record) {
        return mapper.insert(record);
    }

    @Override
    public List<Image> selectByRandom(int num) {
        return mapper.selectByRandom(num);
    }
}
