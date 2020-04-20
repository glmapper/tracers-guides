package com.glmapper.bridge.boot.controller;

import com.glmapper.bridge.boot.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: glmapper (glmapper_2018@163.com) 2019/12/6 2:41 PM
 * @since:
 **/
@RestController
public class TestController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("insert")
    public String insert(){
        TestModel model = new TestModel();
        model.setId(String.valueOf(System.currentTimeMillis()));
        model.setAddTime(new Date());
        model.setMan(true);
        List<String> list = new ArrayList<>();
        list.add("glmapper");
        model.setTags(list);
        model.setName("glmapper");
        model.setAge(123);
        TestModel save = mongoTemplate.save(model);
        return save.toString();
    }
}
