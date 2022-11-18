package producer.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/11/15 17:05
 */
@RestController
public class ZkTestController {
    @GetMapping("/microb/test1/V1")
    public Object bTest1(){
        JSONObject o = new JSONObject();
        o.put("b_test1OOOOOld","xiaosiwolalalalalal");
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
