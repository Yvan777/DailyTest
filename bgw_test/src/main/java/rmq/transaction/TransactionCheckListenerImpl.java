package rmq.transaction;

import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/10/18 14:16
 */
public class TransactionCheckListenerImpl implements TransactionCheckListener {
    //在这里，我们可以根据由MQ回传的key去数据库查询，这条数据到底是成功了还是失败了。
    public LocalTransactionState checkLocalTransactionState(MessageExt msg) {
        System.out.println("未决事务，服务器回查客户端msg =" + new String(msg.getBody().toString()));
        // return LocalTransactionState.ROLLBACK_MESSAGE;
        return LocalTransactionState.COMMIT_MESSAGE;
        // return LocalTransactionState.UNKNOW;
    }
}
