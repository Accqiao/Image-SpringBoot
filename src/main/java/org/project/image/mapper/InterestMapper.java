package org.project.image.mapper;

import org.project.image.entity.Interest;

import java.util.List;

/**
* @author Accqiao
* @description 针对表【interest】的数据库操作Mapper
* @createDate 2022-03-15 16:14:20
* @Entity org.project.image.entity.Interest
*/

public interface InterestMapper {

    int deleteByPrimaryKey(String id);

    int insert(Interest record);

    List<Interest> selectByUid(String uid);


    int insertSelective(Interest record);

    Interest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Interest record);

    int updateByPrimaryKey(Interest record);

}
