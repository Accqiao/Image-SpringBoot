package org.project.image.service;

import org.project.image.entity.Imagelike;

public interface ImageLikeService {

    int newOrChangeRecord(Imagelike record);

    int updateTypeByTwoKey(String uid,String hid);

    Imagelike selectByPrimaryKey(String uid,String hid);
}
