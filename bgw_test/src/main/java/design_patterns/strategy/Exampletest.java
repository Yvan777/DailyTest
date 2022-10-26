package design_patterns.strategy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/9/30 10:05
 */ // 客户端代码会选择具体策略并将其传递给上下文。客户端必须知晓策略之间的差
// 异，才能做出正确的选择。
@RestController
public class Exampletest {
    @Autowired
    private ContextFactory contextFactory;

    //策略模式加工厂模式
    @GetMapping("/test2")
    public void test() {
        Strategy subtraction = contextFactory.getStrategy("concreteStrategyAdd");
        int i = subtraction.execute(1, 13);
        System.out.println(i);
    }

    //纯策略模式
    @GetMapping("/test22")
    public void test22() {
        new Context(11,12,new ConcreteStrategyAdd());
    }
}
