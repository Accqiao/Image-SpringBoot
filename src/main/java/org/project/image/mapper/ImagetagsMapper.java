package org.project.image.mapper;

import org.project.image.entity.Imagetags;

/**
* @author Accqiao
* @description 针对表【imagetags】的数据库操作Mapper
* @createDate 2022-03-30 20:36:57
* @Entity org.project.image.entity.Imagetags
*/
public interface ImagetagsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Imagetags record);

    int insertSelective(Imagetags record);

    Imagetags selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Imagetags record);

    int updateByPrimaryKey(Imagetags record);

}
