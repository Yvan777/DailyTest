package design_patterns.strategy;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/9/30 10:05
 */ // 具体策略会在遵循策略基础接口的情况下实现算法。该接口实现了它们在上下文中的互换性。

@Service
public class ConcreteStrategyAdd implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a + b;
    }

    @Override
    public void syout() {
        System.out.println("add");
    }
}
