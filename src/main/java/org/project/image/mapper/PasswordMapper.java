package org.project.image.mapper;


import org.project.image.entity.Password;

/**
* @author Accqiao
* @description 针对表【password】的数据库操作Mapper
* @createDate 2022-03-15 16:14:02
* @Entity org.project.image.entity.Password
*/
public interface PasswordMapper {

    int deleteByPrimaryKey(String uid);

    int insert(Password record);

    int insertSelective(Password record);

    Password selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(Password record);

    int updateByPrimaryKey(Password record);

}
