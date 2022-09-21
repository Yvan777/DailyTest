package lamdba;

import org.junit.Test;

/**
 * @Description
 * @Author yvan
 * @CreateDate 2022/4/18 4:47 下午
 */
interface Addable {
    int add(int x,int y);
}

public class Testlamdba3 {
    public static void main(String[] args) {
        //有参且有返回值
        useAddable((int a, int b) -> {
            return a+b;
        });

    }

    //junit不能测试static静态方法
    @Test
    void test(){
        useAddable((int a, int b) -> {
            return a+b;
        });
    }

    static void useAddable(Addable a){
        int add = a.add(7, 9);
        System.out.println(add);
    }

}

