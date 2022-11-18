package openfeignConsumer.controller;

import com.alibaba.fastjson.JSONObject;
import openfeignConsumer.feignApi.TheClient;
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
    private TheClient feignApi;

    @GetMapping("/test11")
    public Object test(){
        JSONObject o = new JSONObject();
        o.put("rrr","rrr");
        System.out.println("test1");
        return o;
    }

    @GetMapping("/test22/feignTest")
    public Object test2(){
        return feignApi.feignTest();
    }
}
