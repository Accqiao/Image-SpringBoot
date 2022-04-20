package org.project.image.controller;

import com.alibaba.fastjson.JSONObject;
import org.project.image.entity.History;
import org.project.image.entity.Image;
import org.project.image.entity.ResultObject;
import org.project.image.service.*;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 根据 综合分数 获取一定数量图片
     * @return
     */
    @RequestMapping("score")
    public ResultObject getImagesByScore(@RequestBody String jstr){
        ResultObject resObject = new ResultObject();
        JSONObject jobj = JSONObject.parseObject(jstr);
        String uid = jobj.get("uid").toString();
        int begin = (int)jobj.get("begin");
        int limit = (int)jobj.get("limit");
        List<Object> obj = new ArrayList<>();
        List<Image> imageList = imageService.selectByScore(begin,limit);
        for(Image image : imageList){
            JSONObject jobj2 = new JSONObject();
            jobj2.put("image",image);
            jobj2.put("user",userService.selectByPrimaryKey(image.getUid()));
            jobj2.put("tags",imageTagService.selectByHid(image.getHid()));
            if(uid != null){
                jobj2.put("record",imageLikeService.selectByPrimaryKey(uid,image.getHid()));
            }

            obj.add(jobj2);
        }
        resObject.setResult(true);
        resObject.setMessage("查询成功");
        resObject.setData(obj);
        return resObject;
    }

    /**
     * 根据 浏览量 获取一定数量图片
     * @return
     */
    @RequestMapping("trail")
    public ResultObject getImagesByTrail(@RequestBody String jstr){
        ResultObject resObject = new ResultObject();
        JSONObject jobj = JSONObject.parseObject(jstr);
        String uid = jobj.get("uid").toString();
        int begin = (int)jobj.get("begin");
        int limit = (int)jobj.get("limit");
        List<Object> obj = new ArrayList<>();
        List<Image> imageList = imageService.selectByTrail(begin,limit);
        for(Image image : imageList){
            JSONObject jobj2 = new JSONObject();
            jobj2.put("image",image);
            jobj2.put("user",userService.selectByPrimaryKey(image.getUid()));
            jobj2.put("tags",imageTagService.selectByHid(image.getHid()));
            if(uid != null){
                jobj2.put("record",imageLikeService.selectByPrimaryKey(uid,image.getHid()));
            }
            obj.add(jobj2);
        }
        resObject.setResult(true);
        resObject.setMessage("查询成功");
        resObject.setData(obj);
        return resObject;
    }
    /**
     * 根据 上传时间 获取一定数量图片
     * @return
     */
    @RequestMapping("createtime")
    public ResultObject getImagesByTime(@RequestBody String jstr){
        ResultObject resObject = new ResultObject();
        JSONObject jobj = JSONObject.parseObject(jstr);
        String uid = jobj.get("uid").toString();
        int begin = (int)jobj.get("begin");
        int limit = (int)jobj.get("limit");
        List<Object> obj = new ArrayList<>();
        List<Image> imageList = imageService.selectByCreateTime(begin,limit);
        for(Image image : imageList){
            JSONObject jobj2 = new JSONObject();
            jobj2.put("image",image);
            jobj2.put("user",userService.selectByPrimaryKey(image.getUid()));
            jobj2.put("tags",imageTagService.selectByHid(image.getHid()));
            if(uid != null){
                jobj2.put("record",imageLikeService.selectByPrimaryKey(uid,image.getHid()));
            }
            obj.add(jobj2);
        }
        resObject.setResult(true);
        resObject.setMessage("查询成功");
        resObject.setData(obj);
        return resObject;
    }



    /**
     * 图片上传记录
     * @param uid
     * @return
     */
    @RequestMapping("userhistory")
    public ResultObject getHistory(@RequestParam String uid){
        ResultObject resObject = new ResultObject();
        try{
            List<Object> obj = new ArrayList<>();
            List<Image> list = imageService.selectByUidByTime(uid);
            for(Image image : list){
                JSONObject jobj = new JSONObject();
                jobj.put("image",image);
                jobj.put("user",userService.selectByPrimaryKey(image.getUid()));
                jobj.put("tags",imageTagService.selectByHid(image.getHid()));
                obj.add(jobj);
            }
            resObject.setData(obj);
            resObject.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            resObject.setResult(false);
            resObject.setMessage("操作异常，请稍后再试！");
        }
        return resObject;
    }


    /**
     * 根据 综合分数 获取6张 图片 给首页的
     * @return
     */
    @RequestMapping("carousel")
    public ResultObject getImagesByScoreForCarousel(){
        ResultObject resObject = new ResultObject();
        List<Image> imageList = imageService.selectByScoreByPc(0,6);
        resObject.setResult(true);
        resObject.setMessage("查询成功");
        resObject.setData(imageList);
        return resObject;
    }






    @RequestMapping("onetrail")
    public void oneTrail(@RequestBody String jstr){
        JSONObject jobj = JSONObject.parseObject(jstr);
        String hid = jobj.get("hid").toString();
        trailNumAddOne(hid);
    }


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
