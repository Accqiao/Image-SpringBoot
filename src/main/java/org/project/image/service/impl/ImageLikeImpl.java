package org.project.image.service.impl;

import org.project.image.entity.History;
import org.project.image.entity.Imagelike;
import org.project.image.mapper.ImageMapper;
import org.project.image.mapper.ImagelikeMapper;
import org.project.image.service.ImageLikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImageLikeImpl implements ImageLikeService {
    @Resource
    private ImagelikeMapper mapper;

    @Override
    public int newOrChangeRecord(Imagelike record) {
        return mapper.newOrChangeRecord(record);
    }

    @Override
    public int updateTypeByTwoKey(String uid, String hid) {
        return mapper.updateTypeByTwoKey(uid, hid);
    }

    @Override
    public Imagelike selectByPrimaryKey(String uid, String hid) {
        return mapper.selectByPrimaryKey(uid, hid);
    }

    @Override
    public List<Imagelike> selectByUidByTime(String uid) {
        return mapper.selectByUidByTime(uid);
    }

    @Override
    public List<Imagelike> selectByUidByTimeByLimit(String uid, int begin, int rows) {
        return mapper.selectByUidByTimeByLimit(uid, begin, rows);
    }

    @Override
    public List<String> selectDifferentHid() {
        return mapper.selectDifferentHid();
    }

    @Override
    public List<String> selectUidByHid(String hid) {
        return mapper.selectUidByHid(hid);
    }

    @Override
    public List<String> selectHidByUid(String uid) {
        return mapper.selectHidByUid(uid);
    }
}
