package org.project.image.service.impl;

import org.project.image.entity.Image;
import org.project.image.mapper.ImageMapper;
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

    @Override
    public List<Image> selectByScore(int begin, int num) {
        return mapper.selectByScore(begin, num);
    }

    @Override
    public List<Image> selectByTrail(int begin, int num) {
        return mapper.selectByTrail(begin, num);
    }

    @Override
    public List<Image> selectByLike(int begin, int num) {
        return mapper.selectByLike(begin, num);
    }

    @Override
    public List<Image> selectByUidByTime(String uid) {
        return mapper.selectByUidByTime(uid);
    }

    @Override
    public List<Image> selectByScoreByPc(int begin,int num){
        return mapper.selectByScoreByPc(begin, num);
    }



    @Override
    public void trailNumAddOne(String hid) {
        mapper.trailNumAddOne(hid);
    }

    @Override
    public void likeNumAddOne(String hid) {

        mapper.likeNumAddOne(hid);
    }

    @Override
    public void likeNumSubOne(String hid) {
        mapper.likeNumSubOne(hid);
    }


}
