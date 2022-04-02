package org.project.image.controller;

import com.alibaba.fastjson.JSONObject;
import org.project.image.entity.Imagelike;
import org.project.image.entity.ResultObject;
import org.project.image.service.HistoryService;
import org.project.image.service.ImageLikeService;
import org.project.image.service.ImageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/like")
public class ImageLikeController {
    @Resource
    private HistoryService historyService;
    @Resource
    private ImageService imageService;
    @Resource
    private ImageLikeService imageLikeService;

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
                resObject.setResult(true);
                //resObject.setMessage("操作失败，请稍后再试！");
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


}
