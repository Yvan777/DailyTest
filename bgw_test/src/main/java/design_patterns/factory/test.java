package design_patterns.factory;

import com.apple.eawt.Application;
import design_patterns.DesApplication;
//import org.junit.jupiteer.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/9/30 10:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class test {
    @Autowired
    Map<String,fa> faMap;

    @Test
    public void testaa(){
        fa faa = faMap.get("faa");
        faa.xx();
    }
}