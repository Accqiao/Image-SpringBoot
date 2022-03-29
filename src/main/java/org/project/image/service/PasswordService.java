package org.project.image.service;




import org.project.image.entity.Password;

import java.util.List;

public interface PasswordService {

    int insert(Password record);

    Password selectByPrimaryKey(String uid);

    int updateByPrimaryKey(Password record);
}
