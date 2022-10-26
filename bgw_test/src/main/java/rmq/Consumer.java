package rmq;
import java.util.List;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.*;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * Consumer，订阅消息
 */
public class Consumer {
    public static void main(String[] args) throws InterruptedException, MQClientException {
        //实例化消费者，实例化时需要指定消费组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("quickstart_consumer");
        //设置namesrc地址，有多个的话用";"隔开
        consumer.setNamesrvAddr("10.25.2.217:9876");
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //设置每次消费的消息最大数量，默认是1，即一条条拉取
        consumer.setConsumeMessageBatchMaxSize(10);
        //设置订阅的消息主题topic和标签tags，这里订阅TopicQuickStart主题下的所有消息，所以会收到上面生产者发送的该主题下标签为TagA的消息
        consumer.subscribe("TopicQuickStart", "*");
        //注册消费监听
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                //如果不设置每次消费的消息最大数量，这里的msgs里只会有一条
                System.out.println("消息条数："+msgs.size());
                for(MessageExt msg : msgs){
                    System.out.println(Thread.currentThread().getName()+"收到消息：topic:"+msg.getTopic()+",tags:"+msg.getTags()+",msg:"+new String(msg.getBody()));
                }
                //回复RocketMQ，这条消息消费成功，如果返回的是ConsumeConcurrentlyStatus.RECONSUME_LATER，即表明消息消费失败，那RocketMQ会对这条消息进行重发操作
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //启动消费者，main方法主线程结束后，程序不会停止，进入阻塞状态，来一条消息就触发一次监听事件
        consumer.start();
        System.out.println("Consumer Started.");
    }
}