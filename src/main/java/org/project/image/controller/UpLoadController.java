package org.project.image.controller;
import org.project.image.service.*;

import com.alibaba.fastjson.JSONObject;
import org.project.image.controller.Image.Color;
import org.project.image.controller.Image.DHash;
import org.project.image.controller.Image.MoveFile;
import org.project.image.entity.Image;
import org.project.image.entity.ResultObject;
import org.project.image.entity.Tags;
import org.project.image.entity.User;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/upload")
public class UpLoadController {

    @Resource
    private TagsService tagsService;
    @Resource
    private ImageService imageService;
    @Resource
    private ImageTagService imageTagService;
    @Resource
    private UserService userService;
    @Resource
    private ImageLikeService imageLikeService;


    private String path = "src/main/resources/Image/";

    @RequestMapping("/avatar")
    public ResultObject uploadAvatar(@RequestParam("avatar") MultipartFile multipartFile,@RequestParam("uid") String uid){
        ResultObject resultObj = new ResultObject();
        try {
            //文件名
            String fileName = multipartFile.getOriginalFilename();
            //后缀
            String fileEnd = fileName.substring(fileName.lastIndexOf("."));
            //修改文件名，确保文件名唯一,UUID.randomUUID()数字较长
            //有这么长/例如：1DAF9E46-26F6-4F52-BBA5-422FD0E09270
            String newFileName = UUID.randomUUID() + fileEnd;
            //获取系统当前时间System.currentTimeMillis()当前的毫秒
            //String newFileName = "img"+System.currentTimeMillis() + fileEnd;
            String tempPath = "avatar/" + newFileName;
            String newPath = path + tempPath;

            System.out.println(tempPath + " // " + uid);

            FileOutputStream fileOutputStream = new FileOutputStream(newPath);
            //复制文件//输入流
            FileCopyUtils.copy(multipartFile.getInputStream(),fileOutputStream);
            fileOutputStream.close();

            User user = new User();
            user.setUid(uid);
            user.setHead(tempPath);
            int sign = userService.updateByPrimaryKeySelective(user);
            if(sign == 1){
                resultObj.setResult(true);
                resultObj.setMessage("更新成功！");
                resultObj.setData(tempPath);
                return resultObj;
            }else {
                resultObj.setResult(false);
                resultObj.setMessage("更新失败，请稍后再试！");
                return resultObj;
            }
        } catch (IOException e) {
            e.printStackTrace();
            resultObj.setResult(false);
            resultObj.setMessage("异常错误，请稍后再试！");
            resultObj.setData(e);
            return resultObj;
        }
    }



    @RequestMapping("/finish")
    public ResultObject finish(@RequestBody String jstr){
        //返回的对象
        ResultObject resultObj = new ResultObject();
        try{
            //获取信息转化为json
            JSONObject jobj = JSONObject.parseObject(jstr);
            //创建图片对象
            Image image = new Image();

            String title = jobj.get("title").toString();
            image.setTitle(title);

            String uid = jobj.get("uid").toString();
            image.setUid(uid);
            String hid = jobj.get("hash").toString();
            image.setHid(hid);
            String desc = jobj.get("desc").toString();
            image.setDescription(desc);
            int height = (int)jobj.get("height");
            image.setHeight(height);
            int width = (int)jobj.get("width");
            image.setWidth(width);
            String color = jobj.get("color").toString();
            image.setColor(color);
            String shape = jobj.get("shape").toString();
            image.setType(shape);
            //标签
            List<String> tags =  JSONObject.parseArray(jobj.get("tags").toString(),String.class);

            //默认值
            image.setTrailnum(0);
            image.setLikenum(0);
            image.setScore(0);
            image.setState("yes");

            String tempPath = jobj.get("path").toString();
            String newPath = tempPath.replace("temp","image");
            MoveFile moveFile = new MoveFile();
            Boolean resMove = moveFile.toMoveFile(path,tempPath,newPath);
//            System.out.println("1、移动图片："+ tempPath);
            if(resMove){//图片移动成功
                image.setHref(tempPath.replace("temp","image"));
                int resImg = imageService.insert(image);
                if (resImg == 1){//图片数据库添加成功
//                    System.out.println("2、添加数据库成功");
                    imageTagService.insertList(tags,hid);//添加标签
//                    System.out.println("3、添加标签");
                    resultObj.setMessage("上传成功！");
                    resultObj.setResult(true);
                }else {//移动回去
//                    System.out.println("4、数据库失败，返回图片："+ newPath);
                    moveFile.toMoveFile(path,newPath,tempPath);
                    resultObj.setResult(false);
                    resultObj.setMessage("保存异常，请稍后再试！");
                }
            }else {
                resultObj.setResult(false);
                resultObj.setMessage("图片异常，请稍后再试！");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultObj.setResult(false);
            resultObj.setMessage("异常错误！请稍后再试");
        }
        return resultObj;
    }

    /**
     * 上传图片，返回路径标签的等信息
     * @param multipartFile
     * @return
     */
    @RequestMapping("/image")
    public ResultObject upload(@RequestParam("file") MultipartFile multipartFile){
        ResultObject resultObj = new ResultObject();
        try {
            //文件名
            String fileName = multipartFile.getOriginalFilename();
            //后缀
            String fileEnd = fileName.substring(fileName.lastIndexOf("."));
            //修改文件名，确保文件名唯一,UUID.randomUUID()数字较长
            //有这么长/例如：1DAF9E46-26F6-4F52-BBA5-422FD0E09270
            String newFileName = UUID.randomUUID() + fileEnd;
            //获取系统当前时间System.currentTimeMillis()当前的毫秒
            //String newFileName = "img"+System.currentTimeMillis() + fileEnd;
            String tempPath = "temp/" + newFileName;
            String newPath = path + tempPath;

            //创建目标文件的地址
            FileOutputStream fileOutputStream = new FileOutputStream(newPath);
            //复制文件//输入流
            FileCopyUtils.copy(multipartFile.getInputStream(),fileOutputStream);
            fileOutputStream.close();

            return  getImageInfo(tempPath);
        } catch (IOException e) {
            e.printStackTrace();
            resultObj.setResult(false);
            resultObj.setMessage("异常错误，请稍后再试！");
            resultObj.setData(e);
            return resultObj;
        }
    }

    /**
     * 确定上传图片的信息，确定哈希，使图片唯一
     * @param tempPath
     * @return
     */
    public ResultObject getImageInfo(String tempPath){
        String OPath = "src/main/resources/Image/";
        ResultObject resultObj = new ResultObject();
        String path = OPath + tempPath;
        try {
            FileInputStream fileInputStream = new  FileInputStream(path);
            BufferedImage image = ImageIO.read( fileInputStream);
            fileInputStream.close();
            DHash hashClass = new DHash();
            String hash = hashClass.getDHash(image);
            if(!checkHash(hash)){
                resultObj.setResult(false);
                resultObj.setMessage("已存在相似图片，请勿重复上传！");
                return resultObj;
            }
            System.out.println("新的图片已上传："+ tempPath);
            Color colorClass = new Color();
            String color = colorClass.getAvgRGB(image);
            int width = image.getWidth();
            int height = image.getHeight();
            List<Tags> tagsList = tagsService.selectAllByLevelTags("2");
            JSONObject jobj = new JSONObject();
            jobj.put("color",color);
            jobj.put("path",tempPath);
            jobj.put("width",width);
            jobj.put("height",height);
            jobj.put("hash",hash);
            jobj.put("shape",getShape(width,height));
            jobj.put("tags",tagsList);


            resultObj.setResult(true);
            resultObj.setData(jobj);
            return resultObj;
        } catch (IOException e) {
            e.printStackTrace();
            resultObj.setResult(false);
            resultObj.setMessage("异常错误，请稍后再试！");
            resultObj.setData(e);
            return resultObj;
        }
    }

    /**
     * 根据长宽比 确定标签 pc phone
     * @param width
     * @param height
     * @return
     */
    public String getShape(int width,int height){
        float rate = (float)width / height;
        System.out.println(rate);
        if(rate <= 0.666){
            return "Phone";
        }else if(rate > 1.333){
            return "PC";
        }else {
            return "Square";
        }
    }

    /**
     * 检查数据库是否存在该图片hash
     * @param hash
     * @return
     */
    public boolean checkHash(String hash){
        Image isOk = imageService.selectByPrimaryKey(hash);
        if(isOk == null){
            //没有查询到同一hash，可以插入
            return true;
        }
        System.out.println("该图片已存在！！");
        return false;
    }



    @RequestMapping("test")
    public List<String> Test() throws IOException {
        List<String> users = userService.selectAllUid();
        int userNum = users.size();

        //建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
        int[][] sparseMatrix = new int[userNum][userNum];

        //存储每一个用户对应的不同物品总数  eg: A 3
        Map<String, Integer> userItemLength = new HashMap<>();

        //建立物品到用户的倒排表 eg: a A B
        Map<String, Set<String>> itemUserCollection = new HashMap<>();


        Set<String> items = new HashSet<>();//辅助存储物品集合
        Map<String, Integer> userID = new HashMap<>();//辅助存储每一个用户的用户ID映射
        Map<Integer, String> idUser = new HashMap<>();//辅助存储每一个ID对应的用户映射

        for (int i = 0; i < userNum; i++) {
            String user = users.get(i);
            List<String> imageLikes = imageLikeService.selectHidByUid(user);
            int length = imageLikes.size();
            userItemLength.put(user, length);//eg: A 3
            userID.put(user, i);//用户ID与稀疏矩阵建立对应关系
            idUser.put(i, user);

            for(int j = 0; j < imageLikes.size(); j ++){
                String item = imageLikes.get(j);

                if(items.contains(item)){//如果已经包含对应的物品--用户映射，直接添加对应的用户
                    itemUserCollection.get(item).add(user);
                }else{//否则创建对应物品--用户集合映射
                    items.add(item);
                    itemUserCollection.put(item, new HashSet<String>());//创建物品--用户倒排关系
                    itemUserCollection.get(item).add(user);
                }
            }

        }
        System.out.println("itemUserCollection"+itemUserCollection.toString());

        //计算相似度矩阵【稀疏】
        Set<Map.Entry<String, Set<String>>> entrySet = itemUserCollection.entrySet();
        Iterator<Map.Entry<String, Set<String>>> iterator = entrySet.iterator();
        while(iterator.hasNext()){
            Set<String> commonUsers = iterator.next().getValue();
            for (String user_u : commonUsers) {
                for (String user_v : commonUsers) {
                    if(user_u.equals(user_v)){
                        continue;
                    }
                    sparseMatrix[userID.get(user_u)][userID.get(user_v)] += 1;//计算用户u与用户v都有正反馈的物品总数
                }
            }
        }
        System.out.println("userItemLength"+userItemLength.toString());

/////////////
        System.out.println("Input the user for recommendation:<eg:A>");
        String recommendUser = "admin";
        System.out.println(userID.get(recommendUser));

        //计算用户之间的相似度【余弦相似性】
        int recommendUserId = userID.get(recommendUser);
        for (int j = 0;j < sparseMatrix.length; j++) {
            if(j != recommendUserId){
                System.out.println(idUser.get(recommendUserId)+"--"+idUser.get(j)+"相似度:"+sparseMatrix[recommendUserId][j]/Math.sqrt(userItemLength.get(idUser.get(recommendUserId))*userItemLength.get(idUser.get(j))));
            }
        }

        //计算指定用户recommendUser的物品推荐度
        for(String item: items){//遍历每一件物品
            Set<String> users2 = itemUserCollection.get(item);//得到购买当前物品的所有用户集合
            if(!users2.contains(recommendUser)){//如果被推荐用户没有购买当前物品，则进行推荐度计算
                double itemRecommendDegree = 0.0;
                for(String user: users2){
                    itemRecommendDegree += sparseMatrix[userID.get(recommendUser)][userID.get(user)]/Math.sqrt(userItemLength.get(recommendUser)*userItemLength.get(user));//推荐度计算
                }
                System.out.println("The item "+item+" for "+recommendUser +"'s recommended degree:"+itemRecommendDegree);
            }
        }


//        Color colorClass = new Color();
//        List<Image> imageList = imageService.selectAllImage();
//        for(Image img : imageList){
//            String shape = getShape(img.getWidth(),img.getHeight());
//            FileInputStream fileInputStream = new  FileInputStream(path+img.getHref());
//            BufferedImage image = ImageIO.read( fileInputStream);
//            String color = colorClass.getAvgRGB(image);
//            Image im = new Image();
//            im.setHid(img.getHid());
//            im.setColor(color);
//            im.setType(shape);
//            int num =   imageService.updateByPrimaryKeySelective(im);
//            System.out.println(num+","+color+","+shape+",宽"+img.getWidth()+",高"+img.getHeight());
//        }
        return users;
    }


}
