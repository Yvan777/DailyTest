package rmq.transaction;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/10/18 14:17
 */
public class Consumer {
    public static void main(String[] args) throws InterruptedException, MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("transaction_Consumer");
        consumer.setNamesrvAddr("192.168.100.145:9876;192.168.100.146:9876;192.168.100.149:9876;192.168.100.239:9876");
        consumer.setConsumeMessageBatchMaxSize(10);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("TopicTransactionTest", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                try {
                    for (MessageExt msg : msgs) {
                        System.out.println(msg + ",内容：" + new String(msg.getBody()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;// 重试
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;// 成功
            }
        });
        consumer.start();
        System.out.println("transaction_Consumer Started.");
    }
}