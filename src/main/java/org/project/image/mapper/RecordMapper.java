package org.project.image.mapper;

import org.project.image.entity.Record;

import java.util.List;

/**
* @author Accqiao
* @description 针对表【record】的数据库操作Mapper
* @createDate 2022-03-31 23:17:45
* @Entity org.project.image.entity.Record
*/
public interface RecordMapper {


    int insert(Record record);

    int deleteByPrimaryKey(String hid,String uid,String type);

    List<Record> selectByUser(String uid);

    Record selectByPrimaryKey(String hid,String uid,String type);

    Record selectByAllKey(String hid,String uid,String type);




    int deleteByPrimaryKey(Long id);


    int insertSelective(Record record);

    Record selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

}
