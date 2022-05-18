package org.project.image.mapper;

import org.project.image.entity.Concern;

import java.util.List;

/**
* @author Accqiao
* @description 针对表【concern】的数据库操作Mapper
* @createDate 2022-04-28 10:16:24
* @Entity org.project.image.entity.Concern
*/
public interface ConcernMapper {

    int deleteByPrimaryKey(String uid,String uuid);

    int insert(Concern record);

    List<Concern> selectUserByUser(String uid ,int begin ,int rows);

    List<Concern> selectAllUserByUser(String uid);

    Concern selectByPrimaryKey(String uid,String uuid);

    int selectConcernConut();

    int insertSelective(Concern record);



    int updateByPrimaryKeySelective(Concern record);

    int updateByPrimaryKey(Concern record);

}
