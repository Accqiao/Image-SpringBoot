package org.project.image.mapper;

import org.project.image.entity.Userrecommend;

import java.util.List;

/**
* @author Accqiao
* @description 针对表【userrecommend】的数据库操作Mapper
* @createDate 2022-04-28 01:37:43
* @Entity org.project.image.entity.Userrecommend
*/
public interface UserrecommendMapper {

    int deleteAll();

    int insert(Userrecommend record);

    List<Userrecommend> selectRecommendByUid(String uid,int begin,int num);


    int deleteByPrimaryKey(String id);


    int insertSelective(Userrecommend record);

    Userrecommend selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Userrecommend record);

    int updateByPrimaryKey(Userrecommend record);

}
