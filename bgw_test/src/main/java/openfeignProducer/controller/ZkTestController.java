package openfeignProducer.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/11/15 17:05
 */
@RestController
public class ZkTestController {
    @GetMapping("/test11")
    public Object test(){
        JSONObject o = new JSONObject();
        o.put("rrr","rrr");
        System.out.println("test1");
        return o;
    }

    @GetMapping("/feignTest")
    public Object test22(){
        JSONObject o = new JSONObject();
        o.put("xxx","xxx");
        System.out.println("test1");
        return o;
    }
}
