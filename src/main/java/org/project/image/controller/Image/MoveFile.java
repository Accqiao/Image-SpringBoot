package org.project.image.controller.Image;

import java.io.File;
import java.io.IOException;

public class MoveFile {
    public boolean toMoveFile(String head ,String oldPath ,String newPath){
        String fromPath = head + oldPath;
        String toPath = head + newPath;
        System.out.println("MOVE:从"+oldPath+" 到"+newPath);
        try {
            File file = new File(fromPath);
            if (file.isFile()){
//                File toFile=new File(toPath+"\\"+file.getName());
                File toFile=new File(toPath);
                if (toFile.exists()){
                    System.out.println("MOVE: 文件已存在");
                }
                else{
                    Boolean isOK = file.renameTo(toFile);
                    if(isOK){
                        System.out.println("MOVE: 移动文件成功");
                        return true;
                    }else {
                        System.out.println("MOVE: 移动失败"+isOK);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("MOVE: 异常错误");
        }
        return false;
    }
}
