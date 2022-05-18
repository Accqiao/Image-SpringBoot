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
    public List<Image> selectByScore(int begin, int rows) {
        return mapper.selectByScore(begin, rows);
    }

    @Override
    public List<Image> selectByTrail(int begin, int rows) {
        return mapper.selectByTrail(begin, rows);
    }

    @Override
    public List<Image> selectByLike(int begin, int rows) {
        return mapper.selectByLike(begin, rows);
    }

    @Override
    public List<Image> selectByCreateTime(int begin, int rows) {
        return mapper.selectByCreateTime(begin, rows);
    }

    @Override
    public List<Image> selectByUidByTime(String uid) {
        return mapper.selectByUidByTime(uid);
    }

    @Override
    public List<Image> selectByUidByTimeByLimit(String uid, int begin, int rows) {
        return mapper.selectByUidByTimeByLimit(uid, begin, rows);
    }
    @Override
    public List<Image> selectByScoreByPc(int begin,int num){
        return mapper.selectByScoreByPc(begin, num);
    }

    @Override
    public List<Image> selectAllImage() {
        return mapper.selectAllImage();
    }

    @Override
    public int updateByPrimaryKeySelective(Image record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Image> selectImageByPage(int page, int rows) {
        int begin = (page-1) * rows;
        return mapper.selectImageByPage(begin,rows);
    }

    @Override
    public int selectImageCount() {
        return mapper.selectImageCount();
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
