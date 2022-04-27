package org.project.image.controller;

import com.alibaba.fastjson.JSONObject;
import org.project.image.entity.Imagetags;
import org.project.image.entity.ResultObject;
import org.project.image.entity.Tags;
import org.project.image.service.ImageTagService;
import org.project.image.service.TagsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/imagetags")
public class ImageTagsController {
    @Resource
    private ImageTagService imageTagsService;


    @RequestMapping("change")
    private ResultObject ChangeTags(@RequestBody String jstr){
        JSONObject jobj = JSONObject.parseObject(jstr);
        String type = jobj.get("type").toString();
        String hid = jobj.get("hid").toString();
        String tag = jobj.get("tag").toString();
//        System.out.println(type+"/+"+hid+"/+"+tag);
        ResultObject resObject = new ResultObject();
        int sign = 0;
        if(type.equals("add")){
            Imagetags imagetags = new Imagetags();
            imagetags.setState("yes");
            imagetags.setChangenum(0);
            imagetags.setHid(hid);
            imagetags.setTag(tag);
            sign = imageTagsService.insert(imagetags);
        }else if(type.equals("sub")) {
            sign = imageTagsService.deleteByPrimaryKey(hid, tag);
        }
        System.out.println(sign);
        if(sign == 1){
            resObject.setResult(true);
        }else {
            resObject.setResult(false);
        }
        return resObject;
    }

}
