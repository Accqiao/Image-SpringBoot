package org.project.image.service;

import org.project.image.entity.Tags;

import java.util.List;

public interface TagsService {


    List<Tags> selectAll();

    List<Tags> selectAllByLevelTags(String level);


}
