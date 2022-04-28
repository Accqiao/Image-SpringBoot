package org.project.image.mapper;

import org.project.image.entity.Usersimilarity;

/**
* @author Accqiao
* @description 针对表【usersimilarity】的数据库操作Mapper
* @createDate 2022-04-28 01:37:53
* @Entity org.project.image.entity.Usersimilarity
*/
public interface UsersimilarityMapper {


    int deleteByPrimaryKey(Long id);

    int insert(Usersimilarity record);

    int insertOrUpdate(Usersimilarity record);

    int insertSelective(Usersimilarity record);

    Usersimilarity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Usersimilarity record);

    int updateByPrimaryKey(Usersimilarity record);

}
