package ExAndImp;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.Arrays;
import java.util.Random;

public interface TestAb3 extends TestAb, TestAb2 {

}

class TestAb3er implements TestAb3 {

    @Override
    public void ab3() {
        System.out.println("=====3");
    }

    @Override
    public void ab1() {
        System.out.println("=====1");
    }

    @Override
    public void ab2() {
        System.out.println("=====2");
    }
}

class testCon {
    public static void main(String[] args) {
//        new TestAb3er().ab1();
//        new TestAb3er().ab2();
//        new TestAb3er().ab3();
//        new erzi().gaifangzi();
//        new erzi().zuoti();
       // ThreadPoolExecutor q = new ThreadPoolExecutor("");
        //Integer a=127,b=127,c=128,d=128;
//        int a = 127, b = 127, c = 128, d = 128;
//        System.out.println(a == b);
//        System.out.println(c == d);
        //random 实现10内的随机数
        Random random = new Random();
        int i = random.nextInt(10);
        System.out.println(i);

    }
}

enum xxx{}

interface shengwu {
    void eat();

    void sleep();
}

abstract class ren implements shengwu {
    void gaifangzi() {
        System.out.println("wwww");
    }

    abstract void zuoti();
}

class dad extends ren {

    @Override
    public void eat() {

    }

    @Override
    public void sleep() {

    }

    @Override
    void zuoti() {
        System.out.println("w neng kao qinghua");
    }
}

class erzi extends dad {
    @Override
    void zuoti() {
        System.out.println("w neng kao daoshu");
    }
}


class Example {
     //String str = new String("Wicresoft");
     String str = "Wicresoft";
    char[] ch = {'s', '0', 'A'};

    public static void main(String[] args) {
        Example ex = new Example();
        ex.change(ex.str, ex.ch);
        System.out.print(ex.str + "and" + Arrays.toString(ex.ch));
    }

    void change(String str, char[] ch) {
        str = "Wicrecend";
        ch[1] = 's';
    }
}