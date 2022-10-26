package rmq.order;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.*;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Consumer，订阅消息
 */
public class ConsumerOrderlyA {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_Consumer");
        consumer.setNamesrvAddr("10.25.2.217:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("TopicOrderTest", "*");
        /**
         * 实现了MessageListenerOrderly表示一个队列只会被一个线程取到，第二个线程无法访问这个队列
         * 所以为了保证顺序消费，消费逻辑里不应该有多线程逻辑，比如通过线程池并发消费，这都是不允许的
         */
        consumer.registerMessageListener(new MessageListenerOrderly() {
            AtomicLong consumeTimes = new AtomicLong(0);
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                // 设置自动提交
                context.setAutoCommit(true);
                for (MessageExt msg : msgs) {
                    System.out.println(msg + ",内容：" + new String(msg.getBody()));
                }
                try {
                    TimeUnit.SECONDS.sleep(5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer1 Started.");
    }
}