package design_patterns.strategy;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/9/30 10:05
 */
@Service
public class ConcreteStrategyMultiply implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a * b;
    }
}
