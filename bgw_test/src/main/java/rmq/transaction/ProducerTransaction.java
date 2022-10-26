package rmq.transaction;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.*;
import com.alibaba.rocketmq.common.message.Message;

public class ProducerTransaction {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionCheckListener transactionCheckListener = new TransactionCheckListenerImpl();
        TransactionMQProducer producer = new TransactionMQProducer("transaction_Producer");
        producer.setNamesrvAddr("10.25.2.217:9876");
        // 事务回查最小并发数
        producer.setCheckThreadPoolMinSize(2);
        // 事务回查最大并发数
        producer.setCheckThreadPoolMaxSize(2);
        // 队列数
        producer.setCheckRequestHoldMax(2000);
        producer.setTransactionCheckListener(transactionCheckListener);
        producer.start();
        TransactionExecuterImpl tranExecuter = new TransactionExecuterImpl();
        for (int i = 1; i <= 2; i++) {
            try {
                Message msg = new Message("TopicTransactionTest", "transaction" + i, "KEY" + i,
                        ("Hello RocketMQ " + i).getBytes());
                //发送消息后，消息在MQ的状态是prepared，这时消费者还无法收到这条消息，需等生产者这边的本地事务执行完并发送确认消息后，才能收到这条消息
                SendResult sendResult = producer.sendMessageInTransaction(msg, tranExecuter, null);
                System.out.println(sendResult);
                Thread.sleep(10);
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 100000; i++) {
            Thread.sleep(1000);
        }
        producer.shutdown();
    }
}