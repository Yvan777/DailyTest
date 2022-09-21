package java8;

import org.junit.Test;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/4/21 11:54 上午
 */
public class CyblTest {
     class dog {
         //可以不赋值,有默认值
         String name;
         //final不能不赋值
         final String ss = "";
         void barking(){
            int count = 3;
            int coun2t;
            final String sss;
            for (int i = 0; i < count; i++) {
                System.out.println("wangwang");
            }
        }
    }

    @Test
    public void test() {
//        String a = "ab";
//        String b = "a" + "b";
//        System.out.println(a == b);
            dog d = new dog();
            d.name = "w";
            d.barking();
    }

}
