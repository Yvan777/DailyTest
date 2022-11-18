package java8.lamdba;

import com.beust.jcommander.internal.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java8.lamdba.Testlamdba2.useFlyable;
import static java8.lamdba.Testlamdba3.useAddable;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/4/18 4:47 下午
 */

public class Testlamdba4 {
    public static void main(String[] args) {
        //useAddable((int x,int y) -> {
        //    return x+y;
        //});
        //1.参数的类型可以省略,但是有多个参数的情况下，不能只省略一个
        useAddable((x,y) -> {
            return x+y;
        });

        //useFlyable((String s) ->{
        //    System.out.println(s);
        //});
        //2.如果参数有且只有一个，那么小括号可以省略
        useFlyable( s->{
            System.out.println(s);
        });

        //3.如果代码块的语句只有一条，可以省略大括号和分号
        useFlyable(s -> System.out.println(s));

        //4.如果代码块的语句只有一条，可以省略大括号和分号;如果有return,return也要省略
        useAddable((x,y) -> x+y );

        //List是接口，不能实例化对象

        List<String> newList = new ArrayList<>();

        List<String> stringList = Lists.newArrayList();

        List<String> list = Arrays.asList("1", "2", "3");

        list.forEach(a-> System.out.println("===" + a));

        //新建进程
        new Thread(
                ()-> System.out.println("======")
        ).start();
    }

    interface  xx {
        Object o = new Object();
        String xxS = "sssssssssss";
        void xx();
        void xx2();
        static void xx3() {
            System.out.println("xx3");
        }
    }

    abstract class  xxClass implements xx {
        String xxS;
        @Override
        public void xx(){
            xx.xx3();
        }
        @Override
        public void xx2(){
            System.out.println("xx2");
        }
    }

    class  xxImp implements xx{

        @Override
        public void xx() {

        }

        @Override
        public void xx2() {

        }
        //父类的静态方法并不会被重写
        void xx3(){
            System.out.println("zi xx3");
        }
    }
    @Test
    void  xxtest(){
        //匿名内部类
//        new xxClass() { }.xx();
//        xx.xx3();
//        new xxImp().xx();
        String xxS = xx.xxS;
        System.out.println(xxS);
        new xxImp().xx3();
        xx.xx3();

    }
}

