package org.project.image.controller;

import org.project.image.controller.ImageController.Color;
import org.project.image.controller.ImageController.DHash;
import org.project.image.controller.ImageController.HuaWeiTags;
import org.project.image.controller.ImageController.Main;
import org.project.image.entity.ResultObject;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UpLoadController {
    private String path = "src/main/resources/Image/";
    @RequestMapping("/image")
    public ResultObject upload(@RequestParam("file") MultipartFile multipartFile){
        ResultObject resultObj = new ResultObject();
        //文件名
        String fileName = multipartFile.getOriginalFilename();
        //后缀
        String fileEnd = fileName.substring(fileName.lastIndexOf("."));
        //修改文件名，确保文件名唯一,UUID.randomUUID()数字较长
        //有这么长/例如：1DAF9E46-26F6-4F52-BBA5-422FD0E09270
        //String newFileName = "img"+UUID.randomUUID() + fileEnd;
        //获取系统当前时间System.currentTimeMillis()当前的毫秒
        String newFileName = "img"+System.currentTimeMillis() + fileEnd;
        String tempPath = "temp/" + newFileName;
        String newPath = path + tempPath;
        try {
            //创建目标文件的地址
            FileOutputStream fileOutputStream = new FileOutputStream(newPath);
            //复制文件//输入流
            FileCopyUtils.copy(multipartFile.getInputStream(),fileOutputStream);

            return new Main().getMain(tempPath);
        } catch (IOException e) {
            e.printStackTrace();
            resultObj.setResult(false);
            resultObj.setMessage("图片上传失败！");
            resultObj.setData(e);
            return resultObj;
        }
    }


}
