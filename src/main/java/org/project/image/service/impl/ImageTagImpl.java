package org.project.image.service.impl;

import org.project.image.entity.Imagetags;
import org.project.image.mapper.ImagetagsMapper;
import org.project.image.service.ImageTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImageTagImpl implements ImageTagService {
    @Resource
    private ImagetagsMapper mapper;

    @Override
    public int insert(Imagetags record) {
        return 0;
    }

    @Override
    public void insertList(List<String> taglist,String hid) {
        Imagetags imagetags = new Imagetags();
        imagetags.setState("yes");
        imagetags.setChangenum(0);
        imagetags.setHid(hid);
        for (String tag: taglist){
            imagetags.setTag(tag);
            mapper.insert(imagetags);
        }
    }

    @Override
    public List<Imagetags> selectByHid(String hid) {
        return mapper.selectByHid(hid);
    }

    @Override
    public int deleteByPrimaryKey(String hid, String tag) {
        return mapper.deleteByPrimaryKey(hid, tag);
    }
}
