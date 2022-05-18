package org.project.image.mapper;

import org.project.image.entity.History;
import org.project.image.entity.Imagelike;

import java.util.List;

/**
* @author Accqiao
* @description 针对表【imagelike】的数据库操作Mapper
* @createDate 2022-04-02 22:48:37
* @Entity org.project.image.entity.Imagelike
*/
public interface ImagelikeMapper {

    int newOrChangeRecord(Imagelike record);

    int updateTypeByTwoKey(String uid,String hid);

    Imagelike selectByPrimaryKey(String uid,String hid);

    List<Imagelike> selectByUidByTime(String uid);

    List<Imagelike> selectByUidByTimeByLimit(String uid, int begin, int rows);

    int deleteByPrimaryKey(Long id);

    int insert(Imagelike record);

    int insertSelective(Imagelike record);

    int updateByPrimaryKeySelective(Imagelike record);

    int updateByPrimaryKey(Imagelike record);

    List<String> selectDifferentHid();

    List<String> selectUidByHid(String hid);

    List<String> selectHidByUid(String uid);
}
