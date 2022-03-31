package org.project.image.controller.Image;

import java.io.File;
import java.io.IOException;

public class MoveFile {
    public boolean toMoveFile(String head ,String oldPath ,String newPath){
        String fromPath = head + oldPath;
        String toPath = head + newPath;
        System.out.println(head+" "+oldPath+" "+newPath);
        try {
            File file = new File(fromPath);
            if (file.isFile()){
//                File toFile=new File(toPath+"\\"+file.getName());
                File toFile=new File(toPath);
                if (toFile.exists()){
                    System.out.println("文件已存在");
                    return false;
                }
                else{
                    file.renameTo(toFile);
                    System.out.println("移动文件成功");
                    return true;
                }
            }{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
