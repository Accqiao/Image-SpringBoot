package org.project.image.service;

import org.project.image.entity.Image;
import org.project.image.entity.Imagetags;

import java.util.List;

public interface ImageService {

    Image selectByPrimaryKey(String id);

    int insert(Image record);

    List<Image> selectByRandom(int num);

    List<Image> selectByScore(int begin,int num);

    List<Image> selectByTrail(int begin,int num);

    List<Image> selectByLike(int begin,int num);

    List<Image> selectByCreateTime(int begin,int num);

    List<Image> selectByUidByTime(String uid);

    List<Image> selectByScoreByPc(int begin,int num);







    void trailNumAddOne(String hid);

    void likeNumAddOne(String hid);

    void likeNumSubOne(String hid);
}
