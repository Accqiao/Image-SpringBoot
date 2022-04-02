package org.project.image.mapper;

import org.project.image.entity.History;

/**
* @author Accqiao
* @description 针对表【history】的数据库操作Mapper
* @createDate 2022-04-02 22:48:45
* @Entity org.project.image.entity.History
*/
public interface HistoryMapper {

    int insert(History record);

    int insertOrAddOne(String uid ,String hid);



    int deleteByPrimaryKey(Long id);

    int insertSelective(History record);

    History selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKey(History record);

}
