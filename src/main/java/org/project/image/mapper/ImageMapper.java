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


    List<Image> selectByRandom(int num);

    void trailNumAddOne(String hid);

    void likeNumAddOne(String hid);

    void likeNumSubOne(String hid);

    int insert(Image record);

    Image selectByPrimaryKey(String id);





    int deleteByPrimaryKey(String id);

    int insertSelective(Image record);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

}
