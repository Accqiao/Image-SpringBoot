package org.project.image.mapper;

import org.project.image.entity.Tags;

import java.util.List;

/**
* @author Accqiao
* @description 针对表【tags】的数据库操作Mapper
* @createDate 2022-03-29 17:13:50
* @Entity org.project.image.entity.Tags
*/
public interface TagsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Tags record);

    int insertSelective(Tags record);



    int updateByPrimaryKeySelective(Tags record);

    int updateByPrimaryKey(Tags record);


    Tags selectByPrimaryKey(String id);

    List<Tags> selectAll();

    List<Tags> selectAllByLevelTags(String level);


}
