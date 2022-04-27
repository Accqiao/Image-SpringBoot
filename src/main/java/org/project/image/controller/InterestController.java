package org.project.image.controller;

import com.alibaba.fastjson.JSONObject;
import org.project.image.entity.Imagetags;
import org.project.image.entity.Interest;
import org.project.image.entity.ResultObject;
import org.project.image.service.InterestService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/interest")
public class InterestController {
    @Resource
    private InterestService interestService;

    @RequestMapping("select")
    private ResultObject getInterest(@RequestParam String uid){
        ResultObject resObject = new ResultObject();
        List<Interest> interests = interestService.selectByUid(uid);
        resObject.setResult(true);
        resObject.setMessage("查询成功");
        resObject.setData(interests);
        return resObject;
    }

    @RequestMapping("insert")
    private ResultObject insertTags(@RequestBody String jstr){
        ResultObject resObject = new ResultObject();
        JSONObject jobj = JSONObject.parseObject(jstr);
        String uid = jobj.get("uid").toString();
        String tag = jobj.get("tag").toString();
        Interest interest = new Interest();
        interest.setUid(uid);
        interest.setTag(tag);
        int sign = interestService.insert(interest);
        if(sign == 1){
            resObject.setResult(true);
        }else {
            resObject.setResult(false);
        }
        return resObject;
    }

    @RequestMapping("del")
    private ResultObject delTags(@RequestBody String jstr){
        ResultObject resObject = new ResultObject();
        JSONObject jobj = JSONObject.parseObject(jstr);
        String id = jobj.get("id").toString();
        int sign = interestService.deleteByPrimaryKey(id);
        if(sign == 1){
            resObject.setResult(true);
        }else {
            resObject.setResult(false);
        }
        return resObject;
    }

}
