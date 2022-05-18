package org.project.image.controller;

import com.alibaba.fastjson.JSONObject;
import org.project.image.entity.Concern;
import org.project.image.entity.ResultObject;
import org.project.image.entity.User;
import org.project.image.mapper.ConcernMapper;
import org.project.image.service.ImageService;
import org.project.image.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/concern")
public class ConcernController {
    @Resource
    private UserService userService;
    @Resource
    private ConcernMapper concernMapper;

    @RequestMapping("getUserList")
    private Object getUserList(@RequestParam String uid, int begin , int rows){
        List<Concern> concernList = concernMapper.selectUserByUser(uid,begin,rows);

        List<User> userList = new ArrayList<>();
        for (Concern con : concernList){
            String tempUid = con.getUuid();
            User user = userService.selectByPrimaryKey(tempUid);
            userList.add(user);
        }

        JSONObject backObj = new JSONObject();
        backObj.put("userList",userList);
        backObj.put("count",concernMapper.selectConcernConut());
        return backObj;
    }
    @RequestMapping("getAllList")
    private List<User> getAllList(@RequestParam String uid){
        List<Concern> concernList = concernMapper.selectAllUserByUser(uid);

        List<User> userList = new ArrayList<>();
        for (Concern con : concernList){
            String tempUid = con.getUuid();
            User user = userService.selectByPrimaryKey(tempUid);
            userList.add(user);
        }

        return userList;
    }

    @RequestMapping("cancel")
    public ResultObject cancelUser(@RequestBody String jstr){
        ResultObject resObject = new ResultObject();
        try {
            JSONObject jobj = JSONObject.parseObject(jstr);
            String uid = jobj.get("uid").toString();
            String uuid = jobj.get("uuid").toString();

            int sign = concernMapper.deleteByPrimaryKey(uid,uuid);
            if (sign > 0){
                resObject.setMessage("已取关！");
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

    @RequestMapping("isconcern")
    public ResultObject isconcern(@RequestBody String jstr){
        ResultObject resObject = new ResultObject();
        try {
            JSONObject jobj = JSONObject.parseObject(jstr);
            String uid = jobj.get("uid").toString();
            String uuid = jobj.get("uuid").toString();

            Concern concern = concernMapper.selectByPrimaryKey(uid,uuid);

            resObject.setData(concern);
            resObject.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            resObject.setResult(false);
            resObject.setMessage("操作异常，请稍后再试！");
        }

        return resObject;
    }

    @RequestMapping("new")
    public ResultObject newconcern(@RequestBody String jstr){
        ResultObject resObject = new ResultObject();
        try {
            JSONObject jobj = JSONObject.parseObject(jstr);
            String uid = jobj.get("uid").toString();
            String uuid = jobj.get("uuid").toString();
            Concern concern = new Concern();
            concern.setUid(uid);
            concern.setUuid(uuid);
            concern.setState("yes");
            int sign = concernMapper.insert(concern);
            if (sign > 0){
                resObject.setMessage("关注成功！");
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
