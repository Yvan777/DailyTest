package aspect.controller;

import aspect.anno.Pay;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

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
        Map<String,Object> map = new HashMap<>();
        map.put("score", 10);
//        BigDecimal score2 =  (BigDecimal) map.get("score");
        BigDecimal score2 = new BigDecimal((Integer) map.get("score"));
        System.out.println(score2);

//        final String a = "ab";
//        String b = "a" + "b";
//        String c = "a";
//        String d = "b";
//        System.out.println(a == b);
//        System.out.println(c+d == b);
//        System.out.println((c+d).equals(b));
//        String s = new BigDecimal(9999999.00).toString();
//        String ss = new BigDecimal(9999999).toPlainString();
//        System.out.println(s);
//        System.out.println(ss);
    }
}


