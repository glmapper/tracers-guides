package com.glmapper.bridge.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: glmapper (glmapper_2018@163.com) 2020/4/18 4:21 PM
 * @since:
 **/
@SpringBootApplication
public class MQConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MQConsumerApplication.class,args);
    }
}
