package org.project.image.service;

import org.project.image.entity.Image;
import org.project.image.entity.Imagetags;

import java.util.List;

public interface ImageService {

    Image selectByPrimaryKey(String id);

    int insert(Image record);

    List<Image> selectByRandom(int num);

    List<Image> selectByCreateTime(int begin,int rows);

    List<Image> selectByScore(int begin,int rows);

    List<Image> selectByTrail(int begin,int rows);

    List<Image> selectByLike(int begin,int rows);



    List<Image> selectByUidByTime(String uid);

    List<Image> selectByUidByTimeByLimit(String uid, int begin , int rows);

    List<Image> selectByScoreByPc(int begin,int num);

    List<Image> selectAllImage();

    int updateByPrimaryKeySelective(Image record);

    List<Image> selectImageByPage(int page,int rows);

    int selectImageCount();

    void trailNumAddOne(String hid);

    void likeNumAddOne(String hid);

    void likeNumSubOne(String hid);
}
