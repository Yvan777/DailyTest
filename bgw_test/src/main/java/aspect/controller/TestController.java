package aspect.controller;

import aspect.anno.Pay;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuzhe
 * @date 2018/6/4 9:47
 * @email 1529949535@qq.com
 */
@RestController
public class TestController  {

//    @SysLog("测试")
//    @GetMapping("/test")
//    public void test(){
//        System.out.println("结束");
//        return;
//    }
    @Pay(bank = "jinn",money = "10000")
    @GetMapping("/test")
    public Object test(){
        JSONObject o = new JSONObject();
        o.put("xxx","xxx");
        System.out.println("结束");
        return o;
    }

    @Test
    public void tes2t() {
        final String a = "ab";
        String b = "a" + "b";
        String c = "a";
        String d = "b";
        System.out.println(a == b);
        System.out.println(c+d == b);
        System.out.println((c+d).equals(b));

    }
}


