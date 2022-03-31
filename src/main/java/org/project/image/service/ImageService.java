package org.project.image.service;

import org.project.image.entity.Image;
import org.project.image.entity.Imagetags;

import java.util.List;

public interface ImageService {

    Image selectByPrimaryKey(String id);

    int insert(Image record);

    List<Image> selectByRandom(int num);


}
