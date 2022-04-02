package org.project.image.controller;


import com.alibaba.fastjson.JSONObject;
import org.project.image.controller.Image.Color;
import org.project.image.controller.Image.DHash;
import org.project.image.controller.Image.MoveFile;
import org.project.image.entity.Image;
import org.project.image.entity.ResultObject;
import org.project.image.entity.Tags;
import org.project.image.service.ImageService;
import org.project.image.service.ImageTagService;
import org.project.image.service.TagsService;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UpLoadController {

    @Resource
    private TagsService tagsService;
    @Resource
    private ImageService imageService;
    @Resource
    private ImageTagService imageTagService;


    private String path = "src/main/resources/Image/";



    @RequestMapping("/finish")
    public ResultObject finish(@RequestBody String jstr){
        //返回的对象
        ResultObject resultObj = new ResultObject();
        try{
            //获取信息转化为json
            JSONObject jobj = JSONObject.parseObject(jstr);
            //创建图片对象
            Image image = new Image();

            String title = jobj.get("title").toString();
            image.setTitle(title);

            String uid = jobj.get("uid").toString();
            image.setUid(uid);
            String hid = jobj.get("hash").toString();
            image.setHid(hid);
            String desc = jobj.get("desc").toString();
            image.setDescription(desc);
            int height = (int)jobj.get("height");
            image.setHeight(height);
            int width = (int)jobj.get("width");
            image.setWidth(width);


            //默认值

            image.setTrailnum(0);
            image.setLikenum(0);
            image.setState("yes");


            //标签
            String color = jobj.get("color").toString();
            String shape = jobj.get("shape").toString();
            List<String> tags =  JSONObject.parseArray(jobj.get("tags").toString(),String.class);
            tags.add(color);
            tags.add(shape);


            String tempPath = jobj.get("path").toString();
            String newPath = tempPath.replace("temp","image");
            MoveFile moveFile = new MoveFile();
            Boolean resMove = moveFile.toMoveFile(path,tempPath,newPath);
            System.out.println("1、移动图片："+ tempPath);
            if(resMove){//图片移动成功
                image.setHref(tempPath.replace("temp","image"));
                int resImg = imageService.insert(image);
                if (resImg == 1){//图片数据库添加成功
                    System.out.println("2、添加数据库成功");
                    imageTagService.insertList(tags,hid);//添加标签
                    System.out.println("3、添加标签");
                    resultObj.setMessage("上传成功！");
                    resultObj.setResult(true);
                }else {//移动回去
                    System.out.println("4、数据库失败，返回图片："+ newPath);
                    moveFile.toMoveFile(path,newPath,tempPath);
                    resultObj.setResult(false);
                    resultObj.setMessage("保存异常，请稍后再试！");
                }
            }else {
                resultObj.setResult(false);
                resultObj.setMessage("图片异常，请稍后再试！");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultObj.setResult(false);
            resultObj.setMessage("异常错误！请稍后再试");
        }
        return resultObj;
    }

    /**
     * 上传图片，返回路径标签的等信息
     * @param multipartFile
     * @return
     */
    @RequestMapping("/image")
    public ResultObject upload(@RequestParam("file") MultipartFile multipartFile){
        ResultObject resultObj = new ResultObject();
        try {
            //文件名
            String fileName = multipartFile.getOriginalFilename();
            //后缀
            String fileEnd = fileName.substring(fileName.lastIndexOf("."));
            //修改文件名，确保文件名唯一,UUID.randomUUID()数字较长
            //有这么长/例如：1DAF9E46-26F6-4F52-BBA5-422FD0E09270
            String newFileName = UUID.randomUUID() + fileEnd;
            //获取系统当前时间System.currentTimeMillis()当前的毫秒
            //String newFileName = "img"+System.currentTimeMillis() + fileEnd;
            String tempPath = "temp/" + newFileName;
            String newPath = path + tempPath;

            //创建目标文件的地址
            FileOutputStream fileOutputStream = new FileOutputStream(newPath);
            //复制文件//输入流
            FileCopyUtils.copy(multipartFile.getInputStream(),fileOutputStream);
            fileOutputStream.close();

            return getImageInfo(tempPath);
        } catch (IOException e) {
            e.printStackTrace();
            resultObj.setResult(false);
            resultObj.setMessage("异常错误，请稍后再试！");
            resultObj.setData(e);
            return resultObj;
        }
    }

    /**
     * 确定上传图片的信息，确定哈希，使图片唯一
     * @param tempPath
     * @return
     */
    public ResultObject getImageInfo(String tempPath){
        String OPath = "src/main/resources/Image/";
        ResultObject resultObj = new ResultObject();
        String path = OPath + tempPath;
        try {
            FileInputStream fileInputStream = new  FileInputStream(path);
            BufferedImage image = ImageIO.read( fileInputStream);
            fileInputStream.close();
            DHash hashClass = new DHash();
            String hash = hashClass.getDHash(image);
            if(!checkHash(hash)){
                resultObj.setResult(false);
                resultObj.setMessage("已存在相似图片，请勿重复上传！");
                return resultObj;
            }
            System.out.println("新的图片已上传："+ tempPath);
            Color colorClass = new Color();
            String color = colorClass.getAvgRGB(image);
            int width = image.getWidth();
            int height = image.getHeight();
            List<Tags> tagsList = tagsService.selectAllByLevelTags("2");
            JSONObject jobj = new JSONObject();
            jobj.put("color",color);
            jobj.put("path",tempPath);
            jobj.put("width",width);
            jobj.put("height",height);
            jobj.put("hash",hash);
            jobj.put("shape",getShape(width,height));
            jobj.put("tags",tagsList);


            resultObj.setResult(true);
            resultObj.setData(jobj);
            return resultObj;
        } catch (IOException e) {
            e.printStackTrace();
            resultObj.setResult(false);
            resultObj.setMessage("异常错误，请稍后再试！");
            resultObj.setData(e);
            return resultObj;
        }
    }

    /**
     * 根据长宽比 确定标签 pc phone
     * @param width
     * @param height
     * @return
     */
    public String getShape(int width,int height){
        String shape = "Square";
        if(width/height > 3/4){
            shape = "PC";
        }else if(height/width > 3/4){
            shape = "Phone";
        }

        return shape;
    }

    /**
     * 检查数据库是否存在该图片hash
     * @param hash
     * @return
     */
    public boolean checkHash(String hash){
        Image isOk = imageService.selectByPrimaryKey(hash);
        if(isOk == null){
            //没有查询到同一hash，可以插入
            return true;
        }
        System.out.println("该图片已存在！！");
        return false;
    }


}
