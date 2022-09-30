package design_patterns.factory;

import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/9/30 10:23
 */
@Service
public class faa implements fa{
    @Override
    public String xx() {
        System.out.println("faa");
        return null;
    }
}
