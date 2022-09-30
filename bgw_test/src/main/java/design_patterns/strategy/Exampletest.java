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

    @GetMapping("/test2")
    public void test() {
        Strategy subtraction = contextFactory.getStrategy("concreteStrategyAdd");
        int a = 1;
        int b = 2;
        int i = subtraction.execute(a, b);
        System.out.println(i);
    }
}
