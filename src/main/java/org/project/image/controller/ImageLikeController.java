package org.project.image.controller;

import com.alibaba.fastjson.JSONObject;
import org.project.image.entity.History;
import org.project.image.entity.Image;
import org.project.image.entity.Imagelike;
import org.project.image.entity.ResultObject;
import org.project.image.service.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/like")
public class ImageLikeController {
    @Resource
    private HistoryService historyService;
    @Resource
    private ImageService imageService;
    @Resource
    private ImageLikeService imageLikeService;
    @Resource
    private UserService userService;
    @Resource
    private ImageTagService imageTagService;

    @RequestMapping("new")
    public ResultObject newOrChange(@RequestBody String jstr){
        ResultObject resObject = new ResultObject();
        try {
            JSONObject jobj = JSONObject.parseObject(jstr);
            String uid = jobj.get("uid").toString();
            String hid = jobj.get("hid").toString();
            String type = jobj.get("type").toString();


            Imagelike imagelike = new Imagelike();

            imagelike.setHid(hid);
            imagelike.setUid(uid);
            imagelike.setType(type);

            int sign = imageLikeService.newOrChangeRecord(imagelike);
            if (sign > 0){
                if(type.equals("like")){
                    System.out.println("用户："+uid+"不喜欢？变/收藏：["+hid+",图片]");
                    imageService.likeNumAddOne(hid);//图片浏览量/收藏量 + 1 ;
                    historyService.insertOrAddOne(uid,hid);//用户浏览量 +1
                }else {//不喜欢
                    System.out.println("用户："+uid+"收藏？变/不喜欢：["+hid+",图片]");
                    imageService.likeNumSubOne(hid);//收藏量 - 1 ;
                }



                JSONObject jobj2 = new JSONObject();
                jobj2.put("image",imageService.selectByPrimaryKey(hid));
                jobj2.put("record",imageLikeService.selectByPrimaryKey(uid,hid));
                resObject.setData(jobj2);
                resObject.setResult(true);
            }else {
                resObject.setResult(false);
                resObject.setMessage("操作失败，请稍后再试！");
            }

        }catch (Exception e){
            e.printStackTrace();
            resObject.setResult(false);
            resObject.setMessage("操作异常，请稍后再试！");
        }

        return resObject;
    }

    @RequestMapping("cancel")
    public ResultObject delReacod(@RequestBody String jstr){
        ResultObject resObject = new ResultObject();
        try {
            JSONObject jobj = JSONObject.parseObject(jstr);
            String uid = jobj.get("uid").toString();
            String hid = jobj.get("hid").toString();
            String type = jobj.get("type").toString();

            int sign = imageLikeService.updateTypeByTwoKey(uid,hid);
            if (sign > 0){
                if(type.equals("like")){
                    System.out.println("用户："+uid+"取消收藏：["+hid+",图片]");
                    imageService.likeNumSubOne(hid);//收藏 -1
                }
                JSONObject jobj2 = new JSONObject();
                jobj2.put("image",imageService.selectByPrimaryKey(hid));
                jobj2.put("record",imageLikeService.selectByPrimaryKey(uid,hid));
                resObject.setData(jobj2);
                resObject.setResult(true);
            }else {
                resObject.setResult(false);
                resObject.setMessage("操作失败，请稍后再试！");
            }
        }catch (Exception e){
            e.printStackTrace();
            resObject.setResult(false);
            resObject.setMessage("操作异常，请稍后再试！");
        }

        return resObject;
    }

    @RequestMapping("userhistory")
    public ResultObject getHistory(@RequestParam String uid){
        ResultObject resObject = new ResultObject();
        try{
            List<Object> obj = new ArrayList<>();
            List<Imagelike> list = imageLikeService.selectByUidByTime(uid);
            for(Imagelike imgLike : list){
                JSONObject jobj = new JSONObject();
                Image image = imageService.selectByPrimaryKey(imgLike.getHid());
                jobj.put("image",image);
                jobj.put("record",imgLike);
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

}
