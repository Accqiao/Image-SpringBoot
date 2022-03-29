package org.project.image.controller.ImageController;

import org.project.image.entity.ResultObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    private String OPath = "src/main/resources/Image/";
    public ResultObject getMain(String tempPath){
        ResultObject resultObj = new ResultObject();
        String path = OPath + tempPath;
        try {
            BufferedImage image = ImageIO.read( new FileInputStream(path) );
            int width = image.getWidth();
            int height = image.getHeight();
            String shape = getShape(width,height);

            Color colorClass = new Color();
            DHash hashClass = new DHash();
            //HuaWeiTags tagsClass = new HuaWeiTags();

            String color = colorClass.getAvgRGB(image);
            String hash = hashClass.getDHash(image);

            resultObj.setResult(true);
            resultObj.setMessage("图片上传成功！");
            resultObj.setData(new String[]{
                    color, tempPath, String.valueOf(width), String.valueOf(height), shape
            });
            return resultObj;
        } catch (IOException e) {
            e.printStackTrace();
            resultObj.setResult(false);
            resultObj.setData(e);
            return resultObj;
        }
    }
    public String getShape(int width,int height){
        String shape = "Square";
        if(width/height > 3/4){
            shape = "PC";
        }else if(height/width > 3/4){
            shape = "Phone";
        }

        return shape;
    }
}
