package org.project.image.service;


import org.project.image.entity.Imagelike;

import java.util.List;

public interface ImageLikeService {

    int newOrChangeRecord(Imagelike record);

    int updateTypeByTwoKey(String uid,String hid);

    Imagelike selectByPrimaryKey(String uid,String hid);

    List<Imagelike> selectByUidByTime(String uid);

    List<String> selectDifferentHid();

    List<String> selectUidByHid(String hid);

    List<String> selectHidByUid(String uid);
}
