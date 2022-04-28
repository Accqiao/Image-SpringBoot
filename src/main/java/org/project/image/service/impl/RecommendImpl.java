package org.project.image.service.impl;

import org.project.image.entity.Userrecommend;
import org.project.image.mapper.InterestMapper;
import org.project.image.mapper.UserrecommendMapper;
import org.project.image.service.RecommendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecommendImpl implements RecommendService {

    @Resource
    private UserrecommendMapper mapper;

    @Override
    public void deleteAll() {
        int num = mapper.deleteAll();
        System.out.println("删除"+num+"行旧推荐列表");
    }

    @Override
    public void insertList(List<Userrecommend> recordList) {

        for (Userrecommend ur : recordList){
            int in =  mapper.insert(ur);
//            System.out.println("插入"+in+"行,"+ ur.getHid() +" for "+ ur.getUid()  );
        }
    }

    @Override
    public List<Userrecommend> selectRecommendByUid(String uid, int begin, int num) {
//        int begin = (page-1) * num;
        return mapper.selectRecommendByUid(uid, begin, num);
    }
}
