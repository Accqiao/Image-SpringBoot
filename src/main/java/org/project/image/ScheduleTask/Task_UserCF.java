package org.project.image.ScheduleTask;

import org.project.image.entity.Userrecommend;
import org.project.image.entity.Usersimilarity;
import org.project.image.service.ImageLikeService;
import org.project.image.service.RecommendService;
import org.project.image.service.SimilarityService;
import org.project.image.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class Task_UserCF {
    @Resource
    private UserService userService;
    @Resource
    private ImageLikeService imageLikeService;
    @Resource
    private SimilarityService similarityService;
    @Resource
    private RecommendService recommendService;


    @Scheduled(initialDelay = 3*1000, fixedDelay = 10*60*1000)
    public void runTask() {
        toRecommend();
    }


    public void toRecommend(){
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
//        System.out.println(itemUserCollection.toString());

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
//        System.out.println(userItemLength.toString());

        recommendService.deleteAll();//清除历史推荐信息
        for(String recommendUser : users ){
//            System.out.println("位置"+userID.get(recommendUser));
            //计算用户之间的相似度【余弦相似性】
            List<Usersimilarity> usersimiList = new ArrayList<>();
            int recommendUserId = userID.get(recommendUser);
            for (int j = 0;j < sparseMatrix.length; j++) {
                if(j != recommendUserId){
                    Double similarity = sparseMatrix[recommendUserId][j]/Math.sqrt(userItemLength.get(idUser.get(recommendUserId))*userItemLength.get(idUser.get(j)));
                    Usersimilarity us = new Usersimilarity();
                    us.setUsera(idUser.get(recommendUserId));
                    us.setUserb(idUser.get(j));
                    us.setSimilarity(similarity);
                    usersimiList.add(us);
                    //用户之间的相似度
//                    System.out.println(idUser.get(recommendUserId)+"--"+idUser.get(j)+"相似度:"+sparseMatrix[recommendUserId][j]/Math.sqrt(userItemLength.get(idUser.get(recommendUserId))*userItemLength.get(idUser.get(j))));
                }
            }
            //保存进数据库
            similarityService.insertList(usersimiList);


            List<Userrecommend> userrecommendList = new ArrayList<>();
            //计算指定用户recommendUser的物品推荐度
            for(String item: items){//遍历每一件物品
                Set<String> users2 = itemUserCollection.get(item);//得到购买当前物品的所有用户集合
                if(!users2.contains(recommendUser)){//如果被推荐用户没有购买当前物品，则进行推荐度计算
                    double itemRecommendDegree = 0.0;
                    for(String user: users2){
                        itemRecommendDegree += sparseMatrix[userID.get(recommendUser)][userID.get(user)]/Math.sqrt(userItemLength.get(recommendUser)*userItemLength.get(user));//推荐度计算
                    }
                    Userrecommend us = new Userrecommend();
                    us.setHid(item);
                    us.setUid(recommendUser);
                    us.setRecommend(itemRecommendDegree);
                    userrecommendList.add(us);
                    //推荐图片 推荐度
                    System.out.println("The item "+item+" for "+recommendUser +"'s recommended degree:"+itemRecommendDegree);
                }
            }
            recommendService.insertList(userrecommendList);
        }
    }
}
