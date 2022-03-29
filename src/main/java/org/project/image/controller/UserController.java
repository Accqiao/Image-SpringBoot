package org.project.image.controller;

import com.alibaba.fastjson.JSONObject;
import org.project.image.entity.Password;
import org.project.image.entity.ResultObject;
import org.project.image.entity.User;
import org.project.image.service.PasswordService;
import org.project.image.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private PasswordService passwordService;
    @Resource
    private UserService userService;


    /**
     * 更新用户信息
     * @param jstr
     * @return
     */
    @RequestMapping("update")
    private ResultObject updateInfo(@RequestBody String jstr){
        ResultObject resultObj = new ResultObject();
        JSONObject jobj = JSONObject.parseObject(jstr);
        String uid = (String) jobj.get("uid");
        User user = userService.selectByPrimaryKey(uid);
        Iterator iter = jobj.entrySet().iterator();
        try {
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String setName = "set"+(char)(entry.getKey().toString().charAt(0) - 32)
                        +entry.getKey().toString().substring(1);
                if(setName.equals("setUid")) continue;;
                Method method = user.getClass().getMethod(setName ,String.class);
                method.invoke(user,entry.getValue().toString());

            }
            int sign = userService.updateByPrimaryKey(user);
            System.out.println("修改用户信息sign："+sign);
            if(sign == 1){
                resultObj.setResult(true);
                resultObj.setData(userService.selectByPrimaryKey(uid));
                resultObj.setMessage("修改成功!");
            }else {
                resultObj.setResult(false);
                resultObj.setMessage("修改失败，请稍后再试！");
            }
            return resultObj;
        }catch (Exception e){
            System.out.println("//数据异常" + e);
            resultObj.setResult(false);
            resultObj.setMessage("Error!");
            resultObj.setData(e);
            return resultObj;
        }
    }








//    /**
//     * 获取全部用户列表
//     * @return
//     */
//    @RequestMapping("userlist")
//    private List<User> getUserList(){
//        return userInfoService.selectAll();
//    }
//
//    /**
//     * 根据id获取用户信息
//     * @param uid
//     * @return
//     */
//    @RequestMapping("userinfo/{id}")
//    private User getUserInfo(@PathVariable String uid){
//        return userInfoService.selectByPrimaryKey(uid);
//    }



}

//@PathVariable     |   /user/id       取id
//@RequestParam     |   /user&id=1     取id
//@RequestBody      |                  取json

