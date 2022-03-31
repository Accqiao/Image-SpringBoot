package org.project.image.mapper;

import org.project.image.entity.Image;

import java.util.List;

/**
* @author Accqiao
* @description 针对表【image】的数据库操作Mapper
* @createDate 2022-03-30 20:54:39
* @Entity org.project.image.entity.Image
*/
public interface ImageMapper {

    int insert(Image record);

    Image selectByPrimaryKey(String id);

    List<Image> selectByRandom(int num);


    int deleteByPrimaryKey(Long id);

    int insertSelective(Image record);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

}
