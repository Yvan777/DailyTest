package design_patterns.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/9/30 10:24
 */
@RestController
public class test1 {
    @Autowired
    Map<String,fa> faMap;

    @GetMapping("/test")
    public void testaa(){
        fa faa = faMap.get("faa");
        faa.xx();
    }
}
