package org.project.image.controller;

import com.alibaba.fastjson.JSONObject;
import org.project.image.entity.Password;
import org.project.image.entity.ResultObject;
import org.project.image.service.PasswordService;
import org.project.image.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/safe")
public class LoginController {
    //@PathVariable     |   /user/id       取id
    //@RequestParam     |   /user&id=1     取id
    //@RequestBody      |                  取json

    @Resource
    private PasswordService passwordService;
    @Resource
    private UserService userService;



    /**
     * 登录
     * @param jstr
     * @return
     */
    @RequestMapping("login")
    private ResultObject toLogin(@RequestBody String jstr){
        JSONObject jobj = JSONObject.parseObject(jstr);
        String uid = (String) jobj.get("uid");
        String password = (String) jobj.get("password");
        ResultObject resultObj = new ResultObject();
        try{
            Password pwd = passwordService.selectByPrimaryKey(uid);
            if(uid.equals(pwd.getUid()) && password.equals(pwd.getPassword())){
                resultObj.setResult(true);
                resultObj.setMessage("登录成功!");
                resultObj.setData(userService.selectByPrimaryKey(uid));
                return resultObj;
            }else {
                resultObj.setResult(false);
                resultObj.setMessage("登录失败，密码错误！");
                return resultObj;
            }
        }catch (Exception e){
            resultObj.setResult(false);
            resultObj.setMessage("该账号不存在！");
            resultObj.setData(e);
            return resultObj;
        }
    }


    /**
     * 修改密码
     * @param jstr
     * @return
     */
    @RequestMapping("update")
    private ResultObject toChangePWD(@RequestBody String jstr){
        JSONObject jobj = JSONObject.parseObject(jstr);
        String uid = (String) jobj.get("uid");
        String password = (String) jobj.get("password");
        String newPassword = (String) jobj.get("newPassword");
        ResultObject resultObj = new ResultObject();
        try{
            Password pwd = passwordService.selectByPrimaryKey(uid);
            if(uid.equals(pwd.getUid()) && password.equals(pwd.getPassword())){
                //验证成功可以修改密码
                Password temp = new Password(uid,newPassword);
                int sign = passwordService.updateByPrimaryKey(temp);

                if(sign == 1){
                    resultObj.setResult(true);
                    resultObj.setMessage("修改成功!");
                }else {
                    resultObj.setResult(false);
                    resultObj.setMessage("修改失败，请稍后再试！");
                }
                return resultObj;
            }else {
                //密码错误
                resultObj.setResult(false);
                resultObj.setMessage("密码验证错误！");
                return resultObj;
            }
        }catch (Exception e){
            System.out.println("//数据异常" + e);
            resultObj.setResult(false);
            resultObj.setMessage("Error!");
            resultObj.setData(e);
            return resultObj;
        }
    }




}
