package consumer.controller;

import com.alibaba.fastjson.JSONObject;
import consumer.api.ZkTestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/11/15 17:05
 */
@RestController
public class ZkTestController {
    @Resource
    ZkTestApi zkTestApi;
    @GetMapping("/testApi")
    public Object test(){
        Object o1 = zkTestApi.test22();
        JSONObject o = new JSONObject();
        o.put("rrr",o1);
        System.out.println("testApi");
        return o;
    }
}
