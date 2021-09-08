package com.demo.learn.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author wangchuan.
 * @date 2021/9/7
 */
public class MyMqMsg {

    String topicStr = new String("cly-topic1");
    String nameServerAddr = "127.0.0.1:9876";
    public static final String ROUTER_GROUP = "PID_HIE_ROUTER";
    String groupName = "clyTestGroup";
    String instanceName = "clyTestInstance";

    public void sendMsg() throws Exception {
        DefaultMQProducer mqProducer = new DefaultMQProducer(groupName);
        mqProducer.setNamesrvAddr(nameServerAddr);
        mqProducer.setInstanceName(instanceName);
        mqProducer.start();
        String msgStr = new String("黄所长身为厕所所长，在位期间一直尽职尽责从不曾懈怠，可惜英年早逝，真是天妒英才啊！！");
        MessageExt messageExt = new MessageExt();
        messageExt.setTopic(topicStr);
        messageExt.setBody(msgStr.getBytes());
        SendResult sendResult = mqProducer.send(messageExt);
        System.out.println("生产者发送消息结果为：" + sendResult);

        mqProducer.shutdown();
    }

    public void consumMsg() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(nameServerAddr);
        consumer.subscribe(topicStr, "");
        consumer.setInstanceName("consumer");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println("消费者收到的消息为：" + new String(list.get(0).getBody()));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();

        //consumer.shutdown();
    }

    public static void main(String[] args) throws Exception{
        MyMqMsg myMqMsg = new MyMqMsg();
        myMqMsg.sendMsg();
        myMqMsg.consumMsg();

    }
}
