package design_patterns.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author yvan 用上下文类
 * @CreateDate 2022/9/30 10:05
 */ // 上下文定义了客户端关注的接口。
@Component
public class Context {
     //上下文会维护指向某个策略对象的引用。上下文不知晓策略的具体类。上下文必须通过策略接口来与所有策略进行交互。

    // 需要无参构造器 不然启动会报错
    Context(){

    }
    // 上下文通常会通过构造函数来接收策略对象，同时还提供设置器以便在运行时切换策略。
    Context(int a,int b,Strategy strategy){
        strategy.syout();
        int execute = strategy.execute(a, b);
        System.out.println(execute);
    }
}
