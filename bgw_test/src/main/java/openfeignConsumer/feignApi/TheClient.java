package openfeignConsumer.feignApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/11/16 10:20
 */
@Component
@FeignClient(name = "TESTPR")
public interface TheClient {
    @RequestMapping(path = "/feignTest", method = RequestMethod.GET)
    @ResponseBody
    Object feignTest();
}
