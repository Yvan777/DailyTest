package java8;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/4/22 3:23 下午
 */
public interface TestCj {
    //默认是public static final的
    String a = null;

    default void test90(){

    }
}

class a {
    public static void main(String[] args) {
        String a = TestCj.a;
        System.out.println( "=====" + a);
    }
}
