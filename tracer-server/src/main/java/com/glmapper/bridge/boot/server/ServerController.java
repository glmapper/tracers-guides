package com.glmapper.bridge.boot.server;

import com.alipay.common.tracer.core.tags.SpanTags;
import com.alipay.sofa.tracer.plugin.flexible.annotations.Tracer;
import com.glmapper.bridge.boot.service.FeignService;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: glmapper (glmapper_2018@163.com) 2020/4/18 3:50 PM
 * @since:
 **/
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ServerController {

    private static String       TOPIC  = "DemoTopic";
    private static String       TAGS   = "glmapperTags";

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Autowired
    private FeignService feignService;

    @RequestMapping("server")
    @Tracer
    public String receive() throws Throwable {
        SpanTags.putTags("annotation-name","glmapper");
        SpanTags.putTags("annotation-company","alipay");
        // 发消息
        doSendMessgae();
        return "SUCCESS";
    }

    private void doSendMessgae() throws Throwable{
        org.apache.rocketmq.common.message.Message msg = new org.apache.rocketmq.common.message.Message(TOPIC, TAGS,
                ("Say Hello RocketMQ to Glmapper").getBytes(RemotingHelper.DEFAULT_CHARSET));
        // 调用客户端发送消息
        SendResult sendResult = defaultMQProducer.send(msg);
        System.out.println("sendResult: "+sendResult +"; insert result:  " + insertMongo());
    }

    private String insertMongo(){
        return feignService.insert();
    }
}
