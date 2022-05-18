package org.project.image.controller;

import com.alibaba.fastjson.JSONObject;
import org.project.image.entity.*;
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
    @Resource
    private TagsService tagsService;
    @Resource
    private RecommendService recommendService;
    @Resource
    private InterestService interestService;

    /**
     * 更新图片信息
     * @param jstr
     * @return
     */
    @RequestMapping("update")
    private ResultObject UpdateImage(@RequestBody String jstr){
        ResultObject resObject = new ResultObject();
        JSONObject jobj = JSONObject.parseObject(jstr);
        Image image = JSONObject.toJavaObject(jobj,Image.class);
        int num =   imageService.updateByPrimaryKeySelective(image);
        if( num == 0){
            resObject.setResult(false);
        }else {
            Image newImage = imageService.selectByPrimaryKey(image.getHid());
            resObject.setData(newImage);
            resObject.setResult(true);
        }
        return resObject;
    }

    /**
     * 获取图片的用户，标签信息
     * @param hid
     * @param uid
     * @return
     */
    @RequestMapping("getImageInfo")
    private Object getImageInfo(@RequestParam String hid, String uid){
        JSONObject jobj = new JSONObject();
        jobj.put("user",userService.selectByPrimaryKey(uid));
        jobj.put("tags",imageTagService.selectByHid(hid));
        jobj.put("alltags",tagsService.selectAllByLevelTags("2"));
        return jobj;
    }

    @RequestMapping("getOnlyImageInfo")
    private Object getOnlyImageInfo(@RequestParam String hid){
        JSONObject jobj = new JSONObject();
        Image image = imageService.selectByPrimaryKey(hid);
        jobj.put("image",image);
        jobj.put("user",userService.selectByPrimaryKey(image.getUid()));
        jobj.put("tags",imageTagService.selectByHid(hid));
        return jobj;
    }

    @RequestMapping("select")
    public ResultObject selectImagesByRandom(@RequestParam String uid ,String tag ,int begin,int rows){
        System.out.println(uid+","+tag+","+begin+","+rows);
        ResultObject resObject = new ResultObject();
        try {

            List<Object> obj = new ArrayList<>();
            List<Imagetags> imageList = imageTagService.selectByTag(tag,begin,rows);
            for(Imagetags imTag : imageList){
                Image image = imageService.selectByPrimaryKey(imTag.getHid());
                JSONObject jobj = new JSONObject();
                jobj.put("image",image);
                jobj.put("user",userService.selectByPrimaryKey(image.getUid()));
                if(uid != null){
                    jobj.put("record",imageLikeService.selectByPrimaryKey(uid,image.getHid()));
                }
                obj.add(jobj);
            }
            resObject.setResult(true);
            resObject.setMessage("查询成功");
            resObject.setData(obj);
        }catch (Exception e){
            e.printStackTrace();
            resObject.setResult(false);
            resObject.setMessage("查询成功");
        }

        return resObject;
    }

    /**
     * 随机获取一定数量图片和附属信息
     * @return
     */
    @RequestMapping("random")
    public ResultObject getImagesByRandom(){
        System.out.println("(random/{uid})uid:1");
        ResultObject resObject = new ResultObject();
        List<Object> obj = new ArrayList<>();
        List<Image> imageList = imageService.selectByRandom(10);
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

        System.out.println("(random/{uid})uid:2"+uid);
        ResultObject resObject = new ResultObject();
        List<Object> obj = new ArrayList<>();
        List<Image> imageList = imageService.selectByRandom(10);
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
     * 根据 上传时间 获取一定数量图片
     * @return
     */
    @RequestMapping("createtime")
    public ResultObject getImagesByTime(@RequestParam String uid, int begin , int rows){
        ResultObject resObject = new ResultObject();
        System.out.println(uid+""+begin+""+rows);
        List<Object> obj = new ArrayList<>();
        List<Image> imageList = imageService.selectByCreateTime(begin,rows);
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
     * 根据 综合分数 获取一定数量图片
     * @return
     */
    @RequestMapping("score")
    public ResultObject getImagesByScore(@RequestParam String uid, int begin , int rows){
        ResultObject resObject = new ResultObject();
        List<Object> obj = new ArrayList<>();
        List<Image> imageList = imageService.selectByScore(begin,rows);
        int num = imageList.size();
        for(int i = 0;i<num;i++){
            Image image = imageList.get(i);
            JSONObject jobj = new JSONObject();
            jobj.put("image",image);
            jobj.put("rank",begin+i+1);
            if(uid != null){
                jobj.put("record",imageLikeService.selectByPrimaryKey(uid,image.getHid()));
            }
            obj.add(jobj);
        }
//
//        for(Image image : imageList){
//            JSONObject jobj2 = new JSONObject();
//            jobj2.put("image",image);
//            if(uid != null){
//                jobj2.put("record",imageLikeService.selectByPrimaryKey(uid,image.getHid()));
//            }
//            obj.add(jobj2);
//        }
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
    public ResultObject getImagesByTrail(@RequestParam String uid, int begin , int rows){
        ResultObject resObject = new ResultObject();
        List<Object> obj = new ArrayList<>();
        List<Image> imageList = imageService.selectByTrail(begin,rows);
        int num = imageList.size();
        for(int i = 0;i<num;i++){
            Image image = imageList.get(i);
            JSONObject jobj = new JSONObject();
            jobj.put("image",image);
            jobj.put("rank",begin+i+1);
            if(uid != null){
                jobj.put("record",imageLikeService.selectByPrimaryKey(uid,image.getHid()));
            }
            obj.add(jobj);
        }
//
//        for(Image image : imageList){
//            JSONObject jobj2 = new JSONObject();
//            jobj2.put("image",image);
//            if(uid != null){
//                jobj2.put("record",imageLikeService.selectByPrimaryKey(uid,image.getHid()));
//            }
//            obj.add(jobj2);
//        }
        resObject.setResult(true);
        resObject.setMessage("查询成功");
        resObject.setData(obj);
        return resObject;
    }

    /**
     * 根据 收藏量 获取一定数量图片
     * @return
     */
    @RequestMapping("like")
    public ResultObject getImagesByLike(@RequestParam String uid, int begin , int rows){
        ResultObject resObject = new ResultObject();
        List<Object> obj = new ArrayList<>();
        List<Image> imageList = imageService.selectByLike(begin,rows);
        int num = imageList.size();
        for(int i = 0;i<num;i++){
            Image image = imageList.get(i);
            JSONObject jobj = new JSONObject();
            jobj.put("image",image);
            jobj.put("rank",begin+i+1);
            if(uid != null){
                jobj.put("record",imageLikeService.selectByPrimaryKey(uid,image.getHid()));
            }
            obj.add(jobj);
        }
//
//        for(Image image : imageList){
//            JSONObject jobj2 = new JSONObject();
//            jobj2.put("image",image);
//            if(uid != null){
//                jobj2.put("record",imageLikeService.selectByPrimaryKey(uid,image.getHid()));
//            }
//            obj.add(jobj2);
//        }
        resObject.setResult(true);
        resObject.setMessage("查询成功");
        resObject.setData(obj);
        return resObject;
    }


    /**
     * 获取图片上传记录
     * @param uid
     * @return
     */
    @RequestMapping("userhistory")
    public ResultObject getHistory(@RequestParam String uid, int begin , int rows){
        ResultObject resObject = new ResultObject();
        try{
            List<Object> obj = new ArrayList<>();
            List<Image> list = imageService.selectByUidByTimeByLimit(uid, begin, rows);
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

    /**
     * 根据 上传时间 获取一定数量图片
     * @return
     */
    @RequestMapping("bytag")
    public ResultObject getImagesByTags(@RequestBody String jstr){
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
     * 足迹加一
     * @param jstr
     */
    @RequestMapping("onetrail")
    public void oneTrail(@RequestBody String jstr){
        JSONObject jobj = JSONObject.parseObject(jstr);
        String hid = jobj.get("hid").toString();
        imageService.trailNumAddOne(hid);
    }

    /**
     * 获取分页户图片列表 by最新
     * @return
     */
    @RequestMapping("page")
    private Object getImagePage(@RequestParam int page,int rows){
        JSONObject jobj = new JSONObject();
        jobj.put("value",imageService.selectImageByPage(page, rows));
        jobj.put("count",imageService.selectImageCount());
        return jobj;
    }


    @RequestMapping("byRecommend")
    public ResultObject getImagesByRecommend(@RequestParam String uid, int CFbegin,int Tagbegin){
        ResultObject resObject = new ResultObject();

        //协同过滤推荐数量
        List<Userrecommend> userrecommendList = recommendService.selectRecommendByUid(uid,CFbegin,5);
        List<Object> obj = new ArrayList<>();
        for(Userrecommend us : userrecommendList){
            String hid = us.getHid();
            JSONObject jobj = new JSONObject();
            jobj.put("image",imageService.selectByPrimaryKey(hid));
            jobj.put("record",imageLikeService.selectByPrimaryKey(uid,hid));
            jobj.put("recommend",us.getRecommend());
            jobj.put("type","recom");
            obj.add(jobj);
        }
        int useCFNum = userrecommendList.size();
        int tagNum = 2;
        if(useCFNum < 5){ tagNum = 3; }
        List<Interest> interestList = interestService.selectByUid(uid);

        for(Interest interest: interestList){
            List<Imagetags> imagetagsList = imageTagService.selectByTag(interest.getTag(),Tagbegin,tagNum);
            for(Imagetags imagetags : imagetagsList){
                String hid = imagetags.getHid();
                JSONObject jobj = new JSONObject();
                jobj.put("image",imageService.selectByPrimaryKey(hid));
                jobj.put("record",imageLikeService.selectByPrimaryKey(uid,hid));
                jobj.put("belongTag",imagetags.getTag());
                jobj.put("type","tag");
                obj.add(jobj);
            }

        }
        if((useCFNum + tagNum*interestList.size()) < 10){
            int ranNum = 10 - (useCFNum + tagNum*interestList.size());
            List<Image> imageList = imageService.selectByRandom(ranNum);
            for (Image image : imageList){
                String hid = image.getHid();
                JSONObject jobj = new JSONObject();
                jobj.put("image",image);
                jobj.put("record",imageLikeService.selectByPrimaryKey(uid,hid));
                jobj.put("type","random");
                obj.add(jobj);
            }
        }


        resObject.setResult(true);
        resObject.setMessage("查询成功");
        resObject.setData(obj);
        return resObject;

    }



}
