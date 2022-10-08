package redis.cache;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/10/4 12:29
 */
@RestController
public class redisTest {
    @Autowired
    cacheTestIm cache;

    @GetMapping("/test3")
    @Cacheable(value = "testCacheable", key = "#p0 +'_'+ #p1", cacheManager = "10m" ,unless = "#result==null")
    public String testCacheable(String a, String b) {
        System.out.println("start testCacheable");
        return "testCacheableEnd";
    }

    @GetMapping("/test4")
    public void testRedis() {
    //b方法调用a,会失效 因为注解本身是走的aop 所以这个test是存不进去值的
        String s = testCacheable("11", "22");
        System.out.println(s);
    }

    @GetMapping("/test5")
    public void testRedis2() {
        cache.testCacheable("11", "22");
    }

    @GetMapping("/test6")
    public void testRedis3() {
        cache.testCacheable("11", "33");
    }


}
