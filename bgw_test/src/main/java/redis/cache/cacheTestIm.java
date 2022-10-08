package redis.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/10/8 15:05
 */
@Component("cacheTestIm")
public class cacheTestIm implements  cacheTest{

    @Override
    public String testCacheable(String a, String b) {
        return a+b;
    }
}
