package ExAndImp;

import java8.TestCj;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/4/24 4:53 下午
 */
public class TestJcj {
    //随类加载而加载
    static String JCJ_S = "测试静态变量";
    //随对象创建而建立
    String JCJ = "测试成员变量";
    void testJcj(){
        //随方法调用而出现
        String JCJ_C = "测试局部变量";
        System.out.println(JCJ_C);
    }
}

class TestJcjMain{
    public static void main(String[] args) {
        String jcjS = TestJcj.JCJ_S;
        System.out.println(jcjS);
        String jcj = new TestJcj().JCJ;
        System.out.println(jcj);
        new TestJcj().testJcj();
    }
}