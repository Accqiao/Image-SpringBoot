package org.project.image.controller.Image;

import com.alibaba.fastjson.JSONObject;
import org.project.image.entity.ResultObject;
import org.project.image.entity.Tags;
import org.project.image.service.TagsService;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Main {
    private String OPath = "src/main/resources/Image/";

    private TagsService tagsService;

    public ResultObject getMain(String tempPath){



        ResultObject resultObj = new ResultObject();
        String path = OPath + tempPath;
        try {
            BufferedImage image = ImageIO.read( new FileInputStream(path) );
            Color colorClass = new Color();
            DHash hashClass = new DHash();
            //HuaWeiTags tagsClass = new HuaWeiTags();


            int width = image.getWidth();
            int height = image.getHeight();
            String color = colorClass.getAvgRGB(image);
            String hash = hashClass.getDHash(image);
            List<Tags> tagsList = tagsService.selectAll();
            System.out.println(tagsList);

            JSONObject jobj = new JSONObject();
            jobj.put("color",color);
            jobj.put("path",tempPath);
            jobj.put("width",width);
            jobj.put("height",height);
            //jobj.put("hash",hash);
            jobj.put("shape",getShape(width,height));
            jobj.put("tags",tagsList);



            resultObj.setResult(true);
//            resultObj.setMessage("图片上传成功！");
            resultObj.setData(jobj);
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
