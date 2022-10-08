package redis.cache;

import org.springframework.cache.annotation.Cacheable;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/10/8 15:05
 */
public interface cacheTest {
    @Cacheable(value = "testCacheable", key = "#p0 +'_'+ #p1", cacheManager = "10m" ,unless = "#result==null")
    String testCacheable(String a, String b);
}
