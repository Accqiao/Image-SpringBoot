package org.project.image.service;

import org.project.image.entity.Imagetags;
import org.project.image.mapper.ImageMapper;
import org.project.image.mapper.ImagetagsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public interface ImageTagService {

    int insert(Imagetags record);

    void insertList(List<String> taglist ,String hid);

    List<Imagetags> selectByHid(String hid);

    int deleteByPrimaryKey(String hid,String tag);

    List<Imagetags> selectByTag(String tag ,int begin,int rows);


}
