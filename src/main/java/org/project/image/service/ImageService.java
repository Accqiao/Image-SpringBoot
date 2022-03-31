package org.project.image.service;

import org.project.image.entity.Image;
import org.project.image.entity.Imagetags;

public interface ImageService {

    Image selectByPrimaryKey(String id);

    int insert(Image record);

}
