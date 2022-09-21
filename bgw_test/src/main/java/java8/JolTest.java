package java8;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/4/20 5:00 下午
 */
public class JolTest {
    public static void main(String[] args) {

        //Object o = new Object();

       // System.out.println(ClassLayout.parseInstance(o).toPrintable());

       T t = new T();
       //System.out.println(t.hashCode());
       System.out.println(ClassLayout.parseInstance(t).toPrintable());

    }

    static class T {
        long n2 = 3;
        int n1 = 3;
        boolean n = true;
        void test(){
            long n2 = 3;
        }
    }
    // long 8 + 4 + 8 =20 补4  = 24
    // int 8 + 4 + 4 = 16 不需要补 = 16
    // boolean 8 + 4 + 1 = 13 补3 = 16
}
