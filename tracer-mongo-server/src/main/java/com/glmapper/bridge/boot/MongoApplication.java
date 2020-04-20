package com.glmapper.bridge.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: glmapper (glmapper_2018@163.com) 2019/12/6 2:34 PM
 * @since:
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MongoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class,args);
    }
}
