package org.project.image.mapper;

import org.project.image.entity.Image;

import java.util.List;

/**
* @author Accqiao
* @description 针对表【image】的数据库操作Mapper
* @createDate 2022-04-02 22:05:03
* @Entity org.project.image.entity.Image
*/
public interface ImageMapper {


    void trailNumAddOne(String hid);

    void likeNumAddOne(String hid);

    void likeNumSubOne(String hid);

    int insert(Image record);

    Image selectByPrimaryKey(String id);



    List<Image> selectByRandom(int num);

    List<Image> selectByScore(int begin,int num);

    List<Image> selectByTrail(int begin,int num);

    List<Image> selectByLike(int begin,int num);

    List<Image> selectByCreateTime(int begin,int num);

    List<Image> selectByUidByTime(String uid);

    List<Image> selectByScoreByPc(int begin,int num);






    int deleteByPrimaryKey(String id);

    int insertSelective(Image record);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

}
