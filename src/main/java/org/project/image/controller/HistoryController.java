package org.project.image.controller;

import com.alibaba.fastjson.JSONObject;
import org.project.image.service.HistoryService;
import org.project.image.service.ImageLikeService;
import org.project.image.service.ImageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Resource
    private ImageService imageService;
    @Resource
    private HistoryService historyService;


    @RequestMapping("one")
    public void oneTrail(@RequestBody String jstr){
        JSONObject jobj = JSONObject.parseObject(jstr);
        String hid = jobj.get("hid").toString();
        String uid = jobj.get("uid").toString();

        imageService.trailNumAddOne(hid);
        historyService.insertOrAddOne(uid,hid);

    }

}
