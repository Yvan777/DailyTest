package rmq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

public class Producer {
    public static void main(String[] args) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
        //实例化生产者，实例化时需要指定生产组名
        DefaultMQProducer producer = new DefaultMQProducer("quickstart_producer");
        //设置namesrc地址，有多个的话用";"隔开
        producer.setNamesrvAddr("10.25.2.217:9876");
        //设置重试次数
        producer.setRetryTimesWhenSendFailed(3);
        //启动生产者
        producer.start();
        for(int i=1;i<=100;i++){
            //创建一条消息，指定了消息的主题topic、标签tag、消息的内容
            Message msg = new Message("TopicQuickStart", "TagA", ("Hello RocketMQ "+i).getBytes());
            //发送消息
            SendResult sendResult = producer.send(msg);
            //设置消息的超时时间
            SendResult sendResult2 = producer.send(msg,1000);
            System.out.println(sendResult);
        }
        //关闭生产者，main方法主线程结束，程序终止
        //如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}