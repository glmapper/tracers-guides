package com.glmapper.bridge.boot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * @author: glmapper (glmapper_2018@163.com) 2019/12/6 2:36 PM
 * @since:
 **/
@Document(collection = "test_info")
public class TestModel {
    @Id
    private String id;
    @Field("age")
    private Integer age;
    @Field("is_man")
    private Boolean isMan;
    @Field("name")
    private String name;
    @Field("tags")
    private List<String> tags;
    @Field("add_time")
    private Date addTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getMan() {
        return isMan;
    }

    public void setMan(Boolean man) {
        isMan = man;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
