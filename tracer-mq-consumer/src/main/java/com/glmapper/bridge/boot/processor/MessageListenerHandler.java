package com.glmapper.bridge.boot.processor;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.glmapper.bridge.boot.facade.TestFacade;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: glmapper (glmapper_2018@163.com) 2020/4/5 5:21 PM
 * @since:
 **/
@Component
public class MessageListenerHandler implements MessageListenerConcurrently {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListenerHandler.class);

    private static String       TOPIC  = "DemoTopic";

    @SofaReference(interfaceType = TestFacade.class, binding = @SofaReferenceBinding(bindingType = "bolt"))
    private TestFacade testFacade;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                    ConsumeConcurrentlyContext context) {
        if (CollectionUtils.isEmpty(msgs)) {
            LOGGER.info("receive blank msgs...");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = msgs.get(0);
        String msg = new String(messageExt.getBody());
        if (messageExt.getTopic().equals(TOPIC)) {
            mockConsume(msg);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    private void mockConsume(String msg) {
        String result = testFacade.SayHello(msg);
        LOGGER.info("consumer result: {}.", result);
    }

}