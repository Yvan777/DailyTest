package rmq.transaction;

import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.common.message.Message;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/10/18 14:14
 */
public class TransactionExecuterImpl implements LocalTransactionExecuter {
    public LocalTransactionState executeLocalTransactionBranch(final Message msg, final Object arg) {
        System.out.println("执行本地事务msg = " + new String(msg.getBody()));
        System.out.println("执行本地事务arg = " + arg);
        String tags = msg.getTags();
        if (tags.equals("transaction2")) {
            System.out.println("======我的操作============，失败了  -进行ROLLBACK");
            return LocalTransactionState.ROLLBACK_MESSAGE;  //返回失败并发送回滚消息
        }
        return LocalTransactionState.COMMIT_MESSAGE;  //返回成功并发送确认消息
        // return LocalTransactionState.UNKNOW;
    }
}
