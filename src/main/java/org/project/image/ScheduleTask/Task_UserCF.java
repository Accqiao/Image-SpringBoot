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
        int[][] User_SparseMatrix = new int[userNum][userNum];

        //存储每一个用户对应的不同物品总数  eg: A 3
        Map<String, Integer> User_Item_Length = new HashMap<>();

        //建立物品到用户的倒排表 eg: a A B

        Map<String, Set<String>> Item_User_MAP = new HashMap<>();
        Set<String> items = new HashSet<>();//辅助存储物品集合
        Map<String, Integer> User_ID_MAP = new HashMap<>();//辅助存储每一个用户的用户ID映射
        Map<Integer, String> ID_User_MAP = new HashMap<>();//辅助存储每一个ID对应的用户映射

        for (int i = 0; i < users.size(); i++) {
            String user = users.get(i);
            List<String> imageLikes = imageLikeService.selectHidByUid(user);
            int length = imageLikes.size();
            //用户与物品数量 列表
            User_Item_Length.put(user, length);
            //建立对应关系
            ID_User_MAP.put(i, user);
            User_ID_MAP.put(user, i);
            //创建物品-用户的倒排表
            for(int j = 0; j < imageLikes.size(); j ++){
                String item = imageLikes.get(j);
                if(items.contains(item)){
                    //存在 物品用户 映射则添加对应用户
                    Item_User_MAP.get(item).add(user);
                }else{
                    //不存在则创建 物品用户 映射
                    items.add(item);
                    Item_User_MAP.put(item, new HashSet<String>());//创建物品--用户倒排关系
                    Item_User_MAP.get(item).add(user);
                }
            }
        }

        //计算相似度矩阵【稀疏】
        Set<Map.Entry<String, Set<String>>> SetEntryClass = Item_User_MAP.entrySet();
        Iterator<Map.Entry<String, Set<String>>> entryIterator = SetEntryClass.iterator();
        while(entryIterator.hasNext()){
            Set<String> Com_Users = entryIterator.next().getValue();
            for (String user_u : Com_Users) {
                for (String user_v : Com_Users) {
                    if(user_u.equals(user_v)){
                        continue;
                    }

                    User_SparseMatrix[User_ID_MAP.get(user_u)][User_ID_MAP.get(user_v)] += 1;
                }
            }
        }

        //清除历史推荐信息
        recommendService.deleteAll();
        for(String Curr_User : users ){
            //计算用户之间的余弦相似度
            List<Usersimilarity> User_SimilarityList = new ArrayList<>();
            int Curr_User_Id = User_ID_MAP.get(Curr_User);
            for (int j = 0;j < User_SparseMatrix.length; j++) {
                if(j != Curr_User_Id){
                    Double similarity =
                            User_SparseMatrix[Curr_User_Id][j]
                            /Math.sqrt(
                                    User_Item_Length.get(ID_User_MAP.get(Curr_User_Id))
                                            * User_Item_Length.get(ID_User_MAP.get(j))
                            );
                    Usersimilarity us = new Usersimilarity();
                    us.setUsera(ID_User_MAP.get(Curr_User_Id));
                    us.setUserb(ID_User_MAP.get(j));
                    us.setSimilarity(similarity);
                    //用户之间的相似度  保存至相似度列表
                    User_SimilarityList.add(us);
                }
            }
            //记录用户相似度信息
            similarityService.insertList(User_SimilarityList);

            List<Userrecommend> User_RecommendList = new ArrayList<>();
            //需要计算得分总物品列表
            for(String item: items){
                //需要进行比较的用户集合
                Set<String> Compare_Users = Item_User_MAP.get(item);
                //如果用户已收藏该物品，则不计算，否则计算推荐度
                if(!Compare_Users.contains(Curr_User)){
                    double recommendDegree = 0.0;
                    for(String user: Compare_Users){
                        //通过比较计算该物品的推荐度
                        recommendDegree = recommendDegree + User_SparseMatrix[User_ID_MAP.get(Curr_User)][User_ID_MAP.get(user)]
                                /Math.sqrt(User_Item_Length.get(Curr_User)
                                * User_Item_Length.get(user));
                    }
                    Userrecommend ur = new Userrecommend();
                    ur.setHid(item);
                    ur.setUid(Curr_User);
                    ur.setRecommend(recommendDegree);
                    //推荐图片 推荐度 保存至列表
                    User_RecommendList.add(ur);

                }
            }
            recommendService.insertList(User_RecommendList);
        }
    }
}
