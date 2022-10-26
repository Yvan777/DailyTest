package rmq.order;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import java.util.List;

public class ProducerOrderly {
    public static void main(String[] args) {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("order_Producer");
            producer.setNamesrvAddr("10.25.2.217:9876");
            producer.start();
            for (int i = 1; i <= 5; i++) {
                // 主题：TopicOrderTest，标签：order_1，KEY："KEY" + i，消息内容："order_1 " + i
                Message msg = new Message("TopicOrderTest", "order_1", "KEY" + i, ("order_1 " + i).getBytes());
                // RocketMQ通过MessageQueueSelector中实现的算法来确定消息发送到哪一个队列上
                // RocketMQ默认提供了两种MessageQueueSelector实现：随机/Hash
                // 当然你可以根据业务实现自己的MessageQueueSelector来决定消息按照何种策略发送到消息队列中
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;  //arg就是producer.send方法的最后一个参数，这里是0
                        int index = id % mqs.size();  //队列数量没有事先设置那就是4，0%4=0
                        return mqs.get(index);  //返回下标为0的队列，即这5条消息存放在0号队列中
                    }
                }, 0);
                System.out.println(sendResult);
            }
            for (int i = 1; i <= 5; i++) {
                Message msg = new Message("TopicOrderTest", "order_2", "KEY" + i, ("order_2 " + i).getBytes());
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);  //返回下标为1的队列，即这5条消息存放在1号队列中
                    }
                }, 1);
                System.out.println(sendResult);
            }
            for (int i = 1; i <= 5; i++) {
                Message msg = new Message("TopicOrderTest", "order_3", "KEY" + i, ("order_3 " + i).getBytes());
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);  //返回下标为2的队列，即这5条消息存放在2号队列中
                    }
                }, 2);
                System.out.println(sendResult);
            }
            producer.shutdown();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}