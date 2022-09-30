package design_patterns.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/9/30 10:05
 */ // 上下文定义了客户端关注的接口。
@Component
public class ContextFactory {
    // 上下文会维护指向某个策略对象的引用。上下文不知晓策略的具体类。上下文必须通过策略接口来与所有策略进行交互。
    @Autowired
    private Map<String, Strategy> strategyMap;

    @Autowired//这个注入了多个实现类对象
    private List<Strategy> shapeList;

    // 上下文通常会通过构造函数来接收策略对象，同时还提供设置器以便在运行
    // 时切换策略。
    public Strategy getStrategy(String strategyType) {
        return strategyMap.get(strategyType);
    }
}
