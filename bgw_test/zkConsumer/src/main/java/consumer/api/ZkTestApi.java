package consumer.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/11/15 17:05
 */
@FeignClient(name="TESTPR")
public interface ZkTestApi {
    @GetMapping("/feignTest")
    Object test22();
}
