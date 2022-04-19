package org.project.image.controller;

import com.alibaba.fastjson.JSONObject;
import org.project.image.entity.History;
import org.project.image.entity.Image;
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
@RequestMapping("/history")
public class HistoryController {
    @Resource
    private ImageService imageService;
    @Resource
    private HistoryService historyService;
    @Resource
    private UserService userService;
    @Resource
    private ImageTagService imageTagService;


    @RequestMapping("one")
    public void oneTrail(@RequestBody String jstr){
        JSONObject jobj = JSONObject.parseObject(jstr);
        String hid = jobj.get("hid").toString();
        String uid = jobj.get("uid").toString();
        imageService.trailNumAddOne(hid);
        historyService.insertOrAddOne(uid,hid);

    }

    @RequestMapping("userhistory")
    public ResultObject getHistory(@RequestParam String uid){
        ResultObject resObject = new ResultObject();
        try{
            List<Object> obj = new ArrayList<>();
            List<History> list = historyService.selectByUidByTime(uid);
            for(History his : list){
                JSONObject jobj = new JSONObject();
                Image image = imageService.selectByPrimaryKey(his.getHid());
                jobj.put("image",image);
                jobj.put("record",his);
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
