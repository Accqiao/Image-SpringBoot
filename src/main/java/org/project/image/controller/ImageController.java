package org.project.image.controller;

import com.alibaba.fastjson.JSONObject;
import org.project.image.entity.Image;
import org.project.image.entity.ResultObject;
import org.project.image.service.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Resource
    private ImageService imageService;
    @Resource
    private UserService userService;
    @Resource
    private ImageTagService imageTagService;
    @Resource
    private ImageLikeService imageLikeService;

    /**
     * 随机获取一定数量图片和附属信息
     * @return
     */
    @RequestMapping("random")
    public ResultObject getImagesByRandom(){
        ResultObject resObject = new ResultObject();
        List<Object> obj = new ArrayList<>();
        List<Image> imageList = imageService.selectByRandom(20);
        for(Image image : imageList){
            JSONObject jobj = new JSONObject();
            jobj.put("image",image);
            jobj.put("user",userService.selectByPrimaryKey(image.getUid()));
            jobj.put("tags",imageTagService.selectByHid(image.getHid()));

            obj.add(jobj);
        }
        resObject.setResult(true);
        resObject.setMessage("查询成功");
        resObject.setData(obj);
        return resObject;
    }

    /**
     * 随机获取一定数量图片和附属信息 (包含用户的记录)
     * ps：直接copy的
     * @param uid
     * @return
     */
    @RequestMapping("random/{uid}")
    public ResultObject getImagesByRandomByuser(@PathVariable String uid){
        ResultObject resObject = new ResultObject();
        List<Object> obj = new ArrayList<>();
        List<Image> imageList = imageService.selectByRandom(20);
        for(Image image : imageList){
            JSONObject jobj = new JSONObject();
            jobj.put("image",image);
            jobj.put("user",userService.selectByPrimaryKey(image.getUid()));
            jobj.put("tags",imageTagService.selectByHid(image.getHid()));
            jobj.put("record",imageLikeService.selectByPrimaryKey(uid,image.getHid()));

            obj.add(jobj);
        }
        resObject.setResult(true);
        resObject.setMessage("查询成功");
        resObject.setData(obj);
        return resObject;
    }

    @RequestMapping("onetrail")
    public void oneTrail(@RequestBody String jstr){
        JSONObject jobj = JSONObject.parseObject(jstr);
        String hid = jobj.get("hid").toString();
        trailNumAddOne(hid);
    }
//    @RequestMapping("onelike")
//    public void oneLike(@RequestBody String jstr){
//        JSONObject jobj = JSONObject.parseObject(jstr);
//        String hid = jobj.get("hid").toString();
//        likeNumAddOne(hid);
//    }





    public void trailNumAddOne(String hid){
        imageService.trailNumAddOne(hid);

    }
    public void likeNumAddOne(String hid){
        imageService.likeNumAddOne(hid);
    }
    public void likeNumSubOne(String hid){
        imageService.likeNumSubOne(hid);
    }






}
