package org.project.image.mapper;


import org.project.image.entity.User;

/**
* @author Accqiao
* @description 针对表【userinfo】的数据库操作Mapper
* @createDate 2022-03-15 16:21:08
* @Entity org.project.image.entity.User
*/
public interface UserMapper {

    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
