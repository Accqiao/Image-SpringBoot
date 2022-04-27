package org.project.image.controller;


import org.project.image.entity.Interest;
import org.project.image.entity.ResultObject;
import org.project.image.entity.Tags;
import org.project.image.service.InterestService;
import org.project.image.service.TagsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController {

    @Resource
    private TagsService tagsService;



    /**
     * 获取标签列表
     * @return
     */
    @RequestMapping("list")
    private ResultObject getAllTags(){
        ResultObject resObject = new ResultObject();

        List<Tags> tagsList = tagsService.selectAll();
        resObject.setResult(true);
        resObject.setMessage("查询成功");
        resObject.setData(tagsList);
        return resObject;
    }

    @RequestMapping("select")
    private ResultObject getTags(@RequestParam String level){
        ResultObject resObject = new ResultObject();

        List<Tags> tagsList = tagsService.selectAllByLevelTags(level);
        resObject.setResult(true);
        resObject.setMessage("查询成功");
        resObject.setData(tagsList);
        return resObject;
    }








}
